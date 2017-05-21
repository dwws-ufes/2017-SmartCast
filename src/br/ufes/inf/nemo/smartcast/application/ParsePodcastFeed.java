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
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.Attribute;

import br.ufes.inf.nemo.smartcast.domain.Episode;
import br.ufes.inf.nemo.smartcast.domain.Podcast;
import br.ufes.inf.nemo.smartcast.domain.Tag;
import br.ufes.inf.nemo.smartcast.domain.Tageable;

public class ParsePodcastFeed {

	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String CHANNEL = "channel";
	static final String LANGUAGE = "language";
	static final String COPYRIGHT = "copyright";
	static final String LINK = "link";
	static final String AUTHOR = "author";
	static final String ITEM = "item";
	static final String PUB_DATE = "pubDate";
	static final String GUID = "guid";
	static final String IMAGE = "image";
	static final String URL = "url";
	static final String LAST_BUILD_DATE = "lastBuildDate";
	static final String WIDTH = "width";
	static final String HEIGHT = "height";
	static final String SUBTITLE = "subtitle";
	static final String CATEGORY = "category";
	static final String SUMMARY = "summary";
	static final String DURATION = "duration";
	static final String ENCLOSURE = "enclosure";

	final URL url;

	public ParsePodcastFeed(String feedUrl) throws MalformedURLException {
		this.url = new URL(feedUrl);
	}

	public Podcast readFeed() {
		Podcast feed = new Podcast();
		feed.setTags(new HashMap<>());
		feed.setEpisodes(new ArrayList<>());
		try {
			boolean isFeedHeader = true;
			// Set header values intial to the empty string
			String description = "";
			String title = "";
			String oldTitle = "";
			String link = "";
			String oldLink = "";
			String language = "";
			String copyright = "";
			String author = "";
			String pubDate = "";
			String guid = "";
			String url = "";
			String oldUrl = "";
			String lastBuildDate = "";
			String width = "";
			String height = "";
			String subtitle = "";
			ArrayList<String> category = new ArrayList<>();
			ArrayList<String> categoryFeed = new ArrayList<>();
			String summary = "";
			String duration = "";
			String enclosure = "";

			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = read();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// read the XML document
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					String localPart = event.asStartElement().getName().getLocalPart();
					switch (localPart) {
					case ITEM:
						if (isFeedHeader) {
							isFeedHeader = false;
							putValue(feed, TITLE, title);
							putValue(feed, DESCRIPTION, description);
							putValue(feed, LANGUAGE, language);
							putValue(feed, COPYRIGHT, copyright);
							putValue(feed, LINK, link);
							putValue(feed, AUTHOR, author);
							putValue(feed, URL, url);
							putValue(feed, LAST_BUILD_DATE, lastBuildDate);
							putValue(feed, SUBTITLE, subtitle);
							Tag cat;
							if (feed.getTag(CATEGORY) == null) {
								cat = new Tag();
								feed.putTag(CATEGORY, cat);
							} else {
								cat = feed.getTag(CATEGORY);
							}
							if (cat.getValue() == null) {
								cat.setValue(new ArrayList<>());
							}
							feed.addAllTag(CATEGORY, categoryFeed);
							categoryFeed = new ArrayList<>();
							putValue(feed, SUMMARY, summary);
						}
						event = eventReader.nextEvent();
						break;
					case TITLE:
						title = getCharacterData(event, eventReader);
						break;
					case DESCRIPTION:
						description = getCharacterData(event, eventReader);
						break;
					case LINK:
						link = getCharacterData(event, eventReader);
						break;
					case GUID:
						guid = getCharacterData(event, eventReader);
						break;
					case LANGUAGE:
						language = getCharacterData(event, eventReader);
						break;
					case AUTHOR:
						author = getCharacterData(event, eventReader);
						break;
					case PUB_DATE:
						pubDate = getCharacterData(event, eventReader);
						break;
					case COPYRIGHT:
						copyright = getCharacterData(event, eventReader);
						break;
					case URL:
						url = getCharacterData(event, eventReader);
						break;
					case LAST_BUILD_DATE:
						lastBuildDate = getCharacterData(event, eventReader);
						break;
					case WIDTH:
						width = getCharacterData(event, eventReader);
						break;
					case HEIGHT:
						height = getCharacterData(event, eventReader);
						break;
					case SUBTITLE:
						subtitle = getCharacterData(event, eventReader);
						break;
					case CATEGORY:
						if (event.asStartElement().getName().getPrefix().equals("itunes")) {
							categoryFeed.add(getAttribute(event, "text"));
						} else {
							category.add(getCharacterData(event, eventReader));
						}
						break;
					case SUMMARY:
						summary = getCharacterData(event, eventReader);
						break;
					case DURATION:
						duration = getCharacterData(event, eventReader);
						break;
					case ENCLOSURE:
						enclosure = getAttribute(event, URL);
						break;
					case IMAGE:
						oldTitle = title;
						oldLink = link;
						oldUrl = url;
						break;
					}
				} else if (event.isEndElement()) {
					String endLocal = event.asEndElement().getName().getLocalPart();
					if (endLocal == (ITEM)) {
						Episode ep = new Episode();
						ep.setTags(new HashMap<>());
						putValue(ep, TITLE, title);
						putValue(ep, DESCRIPTION, description);
						putValue(ep, LINK, link);
						putValue(ep, GUID, guid);
						putValue(ep, URL, url);
						putValue(ep, SUBTITLE, subtitle);
						Tag cat;
						if (ep.getTag(CATEGORY) == null) {
							cat = new Tag();
							ep.putTag(CATEGORY, cat);
						} else {
							cat = ep.getTag(CATEGORY);
						}
						if (cat.getValue() == null) {
							cat.setValue(new ArrayList<>());
						}
						ep.addAllTag(CATEGORY, category);
						category = new ArrayList<>();
						putValue(ep, SUMMARY, summary);
						putValue(ep, DURATION, duration);
						putValue(ep, ENCLOSURE, enclosure);
						putValue(ep, PUB_DATE, pubDate);
						feed.addEpisode(ep);
						continue;
					} else if (endLocal == (IMAGE)) {
						Tag tg = new Tag();
						tg.setTags(new HashMap<>());
						putValue(tg, TITLE, title);
						title = oldTitle;
						putValue(tg, URL, url);
						url = oldUrl;
						putValue(tg, LINK, link);
						link = oldLink;
						putValue(tg, WIDTH, width);
						putValue(tg, HEIGHT, height);
						feed.putTag(IMAGE, tg);
					}
				}
			}
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
		return feed;
	}

	private void putValue(Tageable feed, String name, String value) {
		Tag t;
		if (feed.getTag(name) == null) {
			t = new Tag();
			feed.putTag(name, t);
		} else {
			t = feed.getTag(name);
		}
		if (t.getValue() == null) {
			t.setValue(new ArrayList<>());
		}
		t.addValue(value);
	}

	private String getAttribute(XMLEvent event, String attributeName) {
		Iterator it = event.asStartElement().getAttributes();
		while (it.hasNext()) {
			Attribute attr = (Attribute) it.next();
			String name = attr.getName().getLocalPart();
			if (name == attributeName) {
				return attr.getValue();
			}
		}
		return "";
	}

	private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
		String result = "";
		event = eventReader.nextEvent();
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
