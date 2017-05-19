package br.ufes.inf.nemo.smartcast.application;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.Attribute;

import br.ufes.inf.nemo.smartcast.domain.Episode;
import br.ufes.inf.nemo.smartcast.domain.Podcast;
import br.ufes.inf.nemo.smartcast.domain.Tag;

public class ParsePodcastFeed {

	final URL url;

	public ParsePodcastFeed(String feedUrl) {
		try {
			this.url = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public Podcast readFeed() {
		Podcast feed = new Podcast();
		feed.setTags(new HashMap<>());
		feed.setEpisodes(new ArrayList<>());
		System.out.println("Banho");
		int itemCount = 0;
		try {

			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = read();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// read the XML document
			XMLEvent event = eventReader.nextEvent();
			
			while (eventReader.hasNext() && !(event.isStartElement() && getStartEventAsName(event).equals("channel"))) {
				System.out.println("1");
				event = eventReader.nextEvent();
			}
			event = eventReader.nextEvent();
			
			while (eventReader.hasNext() && !(event.isEndElement() && getEndEventAsName(event).equals("channel"))) {
				System.out.println("2");
				if (event.isStartElement()) {

					String name = getStartEventAsName(event);

					Tag tag = readTag(event, eventReader);

					if (name.equals("item")) {
						itemCount++;
						while((event.isStartElement() && getStartEventAsName(event).equals("item")) || (event.isCharacters() && getCharacterData(event).trim().isEmpty())){
							event = eventReader.nextEvent();
						}
						Episode ep = new Episode();
						ep.setTags(new HashMap<>());
						while (eventReader.hasNext()) {
							if (event.isStartElement()) {
								String epTagName = getStartEventAsName(event);

								Tag epTag = readTag(event, eventReader);
								ep.putTag(epTagName, epTag);

							}else if (event.isEndElement() && getEndEventAsName(event).equals("item")){
								break;
							}
							event = eventReader.nextEvent();
						}

						feed.addEpisode(ep);
					}

					feed.putTag(name, tag);

				}
				if(eventReader.hasNext()){
					event = eventReader.nextEvent();
				}
			}
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Item Count: " + itemCount);
		return feed;
	}

	private String getStartEventAsName(XMLEvent evt) {
		String name = "";
		String prefix = evt.asStartElement().getName().getPrefix();
		String localPart = evt.asStartElement().getName().getLocalPart();

		if (!prefix.isEmpty()) {
			name += prefix + ":";
		}

		name += localPart;
		return name;
	}

	private String getEndEventAsName(XMLEvent evt) {
		String name = "";
		String prefix = evt.asEndElement().getName().getPrefix();
		String localPart = evt.asEndElement().getName().getLocalPart();

		if (!prefix.isEmpty()) {
			name += prefix + ":";
		}

		name += localPart;
		return name;
	}

	private void readAttribute(Tag t, XMLEvent event) {

		t.setAttributes(new HashMap<>());

		Iterator attribute = event.asStartElement().getAttributes();

		while (attribute.hasNext()) {
			Attribute attr = (Attribute) attribute.next();
			String value = attr.getValue();
			String name = attr.getName().getLocalPart();
			t.putAttribute(name, value);
		}
		Iterator namespaces = event.asStartElement().getNamespaces();

		while (namespaces.hasNext()) {
			Namespace namesp = (Namespace) namespaces.next();
			String name = namesp.getName().getPrefix() + ":" + namesp.getName().getLocalPart();
			String value = namesp.getValue();
			t.putAttribute(name, value);
		}
	}

	private Tag readTag(XMLEvent evt, XMLEventReader eventReader) throws XMLStreamException {

		Tag t = new Tag();
		readAttribute(t, evt);
		t.setTags(new HashMap<>());

		while (eventReader.hasNext()) {
			evt = eventReader.nextEvent();
			if (evt.isStartElement()) {
				t.putTag(getStartEventAsName(evt), readTag(evt, eventReader));
			} else if (evt.isCharacters()) {
				String s = getCharacterData(evt);
				if (s.trim().isEmpty()) {
					continue;
				} else {
					if(t.getValue() == null){
						t.setValue(new ArrayList<>());
					}
					t.addValue(s);
				}
			} else if (evt.isEndElement()) {
				break;
			}
		}
		return t;

	}

	private String getCharacterData(XMLEvent event) throws XMLStreamException {
		String result = "";
		if (event instanceof Characters) {
			result = event.asCharacters().getData();
		}
		return result;
	}

	private InputStream read() {
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
