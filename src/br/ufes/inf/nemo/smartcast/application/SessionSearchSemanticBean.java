package br.ufes.inf.nemo.smartcast.application;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.bean.SessionScoped;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import br.ufes.inf.nemo.smartcast.domain.Listener;
import br.ufes.inf.nemo.smartcast.domain.Podcast;
import br.ufes.inf.nemo.smartcast.domain.Tag;
import br.ufes.inf.nemo.smartcast.domain.Tageable;
import br.ufes.inf.nemo.smartcast.persistence.PodcastDAO;

@SessionScoped
@Stateful

public class SessionSearchSemanticBean extends SessionSearchSemantic{
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	/** The DAO for Academic objects. */
	@EJB
	private PodcastDAO podcastDAO;

	Podcast currentPodcast = new Podcast();

	/** The current user logged in. */
	
	public Podcast getCurrentPodcast() {
		//System.out.println("Entrou");
		return currentPodcast;
	}
	
	public void savePodcastSearch(ResultSet results){
	   
		while (results.hasNext()) {
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
			
			currentPodcast.setUrl(thing.getURI());
			currentPodcast.setTags(new ArrayList<>());
			//currentPodcast.putTag();
			//currentPodcast.putTag("description", literalTitle.getValue());
			
			putValue(currentPodcast,"title", literalTitle.getValue().toString());
			putValue(currentPodcast, "description", auxDesc);
			podcastDAO.save(currentPodcast);
			//return currentPodcast;
		}
		
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
