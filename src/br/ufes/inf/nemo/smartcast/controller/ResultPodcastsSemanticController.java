package br.ufes.inf.nemo.smartcast.controller;



import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import br.ufes.inf.nemo.smartcast.application.ManagePodcastsService;
import br.ufes.inf.nemo.smartcast.domain.Podcast;

@ManagedBean
@SessionScoped
public class ResultPodcastsSemanticController {
	
	private List<Podcast> podcasts = new ArrayList<>();
	
	private String title;
	@EJB ManagePodcastsService managePodcastService;
	

	

	public String searchPodcastSemantic(){
		//Podcast Semantic
	
			    System.out.println("Entrouuu");
				System.out.println("Title title = "  +title);
			//	System.out.println("Title podcasts = " + podcasts.getTitle());
		
     			String query = "PREFIX mlo: <http://purl.org/net/mlo/> " +
     			"PREFIX dc: <http://purl.org/dc/terms/> " +
            	"PREFIX wm: <http://www.w3.org/TR/2010/WD-mediaont-10-20100608/> " + 	
            	"PREFIX dbo: <http://dbpedia.org/property/> " +
        		"SELECT ?thing ?url ?title ?desc  " +
    			"FROM <http://data.open.ac.uk/context/podcast> " +
				"WHERE { " + 
				    "?thing dbo:url ?url . " +
					"?thing dc:title ?title ." +
				    "?thing dc:description ?desc ." + 
					"FILTER EXISTS {" +
					"{ ?thing a <http://data.open.ac.uk/podcast/ontology/PodcastCollection> }" +
					"}" +
					"FILTER regex(str(?title), '" +title+ "' , 'i' )" +
				"}";
				QueryExecution queryExecution = 
				QueryExecutionFactory.sparqlService("http://data.open.ac.uk/sparql", query);
				ResultSet results = queryExecution.execSelect();
////	
////				//podcasts = service.podcasts(results);
////				//setPodcasts(podcasts);
				//while (results.hasNext()) {
				
					QuerySolution querySolution = results.next();
					//System.out.println(querySolution);
					Resource thing = (Resource) querySolution.get("thing");
				    System.out.println("Thing: "+thing.getURI());
				    Literal literalTitle = querySolution.getLiteral("title");
					System.out.println("Title:"+literalTitle.getValue());
					Literal literalDesc = querySolution.getLiteral("desc");
					System.out.println("Description:"+literalDesc.getValue());
					Resource url = (Resource) querySolution.get("url");
				    System.out.println("URL: "+url.getURI());
				    this.podcasts = managePodcastService.search(url.getURI().toString());
				    
			//  }
	
		return "/resultSemantic/index.faces#portfolio?faces-redirect=true";

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public List<Podcast> getPodcasts(){
		System.out.println("Retornou lista podcast");
		System.out.println(podcasts);
		return podcasts;
	}
	




}
