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


import br.ufes.inf.nemo.smartcast.application.SessionSearchSemantic;

@ManagedBean
@SessionScoped
public class ManagePodcastsSemanticController {
	private String searchStringSemantic;
	
	/** Insert Podcast to Search. */
	@EJB private SessionSearchSemantic sessionSearchSemantic;
	public String getSearchStringSemantic() {
		System.out.printf("Podcast:",searchStringSemantic);
		return searchStringSemantic;
	}

	public void setSearchStringSemantic(String searchStringSemantic) {
		System.out.println("SET");
		this.searchStringSemantic = searchStringSemantic;
		//this.podcasts = managePodcastService.search(searchStringSemantic);
		System.out.printf("Podcast:",searchStringSemantic);
	}
	
	public String searchPodcastSemantic(){
		//Podcast Semantic
		System.out.println("Podcast Semantic");
		System.out.println(getSearchStringSemantic());
		if(searchStringSemantic == null || searchStringSemantic.equals("")){
			System.out.println("Podcast1");
			//this.podcasts = managePodcastService.getSome();
		}else{
			System.out.println("Entrar nesse ponto");
			System.out.println(searchStringSemantic);
			//System.out.println(this.podcasts);
			//this.podcasts = managePodcastService.search(searchString);
			//String inputFileName1 = "/home/pablo/podcast.rdf";
			//Model model1 =	ModelFactory.createDefaultModel();	
			//InputStream	in1	= FileManager.get().open(inputFileName1);	
			//if(in1 == null)	
			//{
			//	throw new IllegalArgumentException( "File:"+inputFileName1+"not found");	
			//}	
				  
			//model1.read(in1,"" );	
				  	
				  	
				 	
			    
			  
					  
			  
			if(searchStringSemantic == null || searchStringSemantic.equals("")){
				//System.out.println("Podcast1");
				System.out.println("Podcst1");
				//this.podcasts = managePodcastService.getSome();
			}else{
			
				//System.out.println(this.podcasts);
				String query = "PREFIX mlo: <http://purl.org/net/mlo/> " +
				"PREFIX dc: <http://purl.org/dc/terms/> " +
				"PREFIX wm: <http://www.w3.org/TR/2010/WD-mediaont-10-20100608/> " + 	
				"SELECT ?thing ?title ?desc  " +
				"FROM <http://data.open.ac.uk/context/podcast> " +
				"WHERE { " + 
					"?thing dc:title ?title ." +
				    "?thing dc:description ?desc ." + 
					"FILTER EXISTS {" +
					"{ ?thing a <http://data.open.ac.uk/podcast/ontology/PodcastCollection> }" +
					"}" +
					"FILTER regex(str(?title), '" +searchStringSemantic+ "' , 'i' )" +
				"}";
				QueryExecution queryExecution = 
				QueryExecutionFactory.sparqlService("http://data.open.ac.uk/sparql", query);
				ResultSet results = queryExecution.execSelect();
	
				sessionSearchSemantic.savePodcastSearch(results);
				while (results.hasNext()) {
					
					QuerySolution querySolution = results.next();
					//System.out.println(querySolution);
					Resource thing = (Resource) querySolution.get("thing");
				    System.out.println("Thing: "+thing.getURI());
				    Literal literalTitle = querySolution.getLiteral("title");
					System.out.println("Title:"+literalTitle.getValue());
					Literal literalDesc = querySolution.getLiteral("desc");
					System.out.println("Description:"+literalDesc.getValue());
					
//					singlePodcasts.setUrl(thing.getURI());
//					String auxDesc = literalTitle.getValue().toString();
//					auxDesc = auxDesc.substring(0, 250);
//					singlePodcasts.setUrl(thing.getURI().toString());
//					
//					//podcast.putTag("name", literalTitle.getValue().toString());
//					//feed.putTag("description", auxDesc);
//					
//					//tags.add(feed);
//					singlePodcasts.setTags(tags);
//					//singlePodcasts.setName(literalTitle.getValue().toString());
//					//singlePodcasts.setDesc(literalDesc.getValue().toString().substring(0, 250));
//					podcasts.add(singlePodcasts);
			  }
				
			}
		}
		return null;
	}

}
