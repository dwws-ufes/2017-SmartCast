package br.ufes.inf.nemo.smartcast.application;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import br.ufes.inf.nemo.smartcast.domain.Podcast;
import br.ufes.inf.nemo.smartcast.domain.Tag;
import br.ufes.inf.nemo.smartcast.domain.Tageable;
import br.ufes.inf.nemo.smartcast.persistence.PodcastDAO;

@SessionScoped


@ManagedBean(name = "sessionSearchSemantic")

public class SessionSearchSemantic{

	/** The DAO for Academic objects. */
	@EJB
	private PodcastDAO podcastDAO;

	Podcast currentPodcast1 = new Podcast();

	/** The current user logged in. */
	
	public Podcast getCurrentPodcast() {
		//System.out.println("Entrou");
		return currentPodcast1;
	}
	

	public List<Podcast> podcasts(ResultSet results) {	
		List<Podcast> result = new ArrayList<Podcast>();  
		while (results.hasNext()) {
			Podcast currentPodcast = new Podcast();
			QuerySolution querySolution = results.next();
			//System.out.println(querySolution);
			Resource thing = (Resource) querySolution.get("thing");
		    System.out.println("Thing: "+thing.getURI());
		    Literal literalTitle = querySolution.getLiteral("title");
			System.out.println("Title:"+literalTitle.getValue());
			Literal literalDesc = querySolution.getLiteral("desc");
			System.out.println("Description:"+literalDesc.getValue());
			String auxDesc = literalTitle.getValue().toString();
			if (auxDesc.length() > 250){
				auxDesc = auxDesc.substring(0, 250);
			}
			//currentPodcast = null;
			currentPodcast.setUrl(thing.getURI());
			currentPodcast.setTags(new ArrayList<>());
			//currentPodcast.putTag();
			//currentPodcast.putTag("description", literalTitle.getValue());
			currentPodcast.putTag("title", literalTitle.getValue().toString());
			currentPodcast.putTag("description", auxDesc);
			
			//putValue(currentPodcast,"title", literalTitle.getValue().toString());
			//putValue(currentPodcast, "description", auxDesc);
			//podcastDAO.save(currentPodcast);
			System.out.println(currentPodcast.getTitle());
			
			result.add(currentPodcast);
			
		
		}
		System.out.println("result:");
		System.out.println(result);
		return result;
		
	}
	private void putValue(Tageable feed, String name, String value) {
		Tag t;
		if (feed.getTag(name) == null) {
			t = new Tag();
			t.setName(name);
			feed.putTag(t);
		} else {
			t = feed.getTag(name);
		}
		if (t.getValue() == null) {
			t.setValue(new ArrayList<>());
		}
		t.addValue(value);
	}

}