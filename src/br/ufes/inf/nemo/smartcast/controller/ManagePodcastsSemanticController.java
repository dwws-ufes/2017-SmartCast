package br.ufes.inf.nemo.smartcast.controller;



import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import br.ufes.inf.nemo.smartcast.application.SessionSearchSemantic;
import br.ufes.inf.nemo.smartcast.domain.Podcast;

@ManagedBean
@SessionScoped
public class ManagePodcastsSemanticController {
	private String searchStringSemantic;
	private String title;
		
	private List<Podcast> podcasts = new ArrayList<>();
	
	@ManagedProperty("#{sessionSearchSemantic}")
    private SessionSearchSemantic service;
	
	/** Insert Podcast to Search. */
	//@EJB private SessionSearchSemantic sessionSearchSemantic;
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
		}else{
			System.out.println("Entrar nesse ponto");
			System.out.println(searchStringSemantic);	  
			if(searchStringSemantic == null || searchStringSemantic.equals("")){
				//System.out.println("Podcast1");
				System.out.println("Podcst1");
				return null;
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
	
				podcasts = service.podcasts(results);
				//setPodcasts(podcasts);
				while (results.hasNext()) {
					
					QuerySolution querySolution = results.next();
					//System.out.println(querySolution);
					Resource thing = (Resource) querySolution.get("thing");
				    System.out.println("Thing: "+thing.getURI());
				    Literal literalTitle = querySolution.getLiteral("title");
					System.out.println("Title:"+literalTitle.getValue());
					Literal literalDesc = querySolution.getLiteral("desc");
					System.out.println("Description:"+literalDesc.getValue());
					
			  }
				
			}
		}
		return "/searchSemantic/index.faces#portfolio?faces-redirect=true";
	}
	public String searchResultPodcastSemantic(){
		System.out.println("Entrou no command button");
		System.out.println(title);
		return "/resultSemantic/index.faces#portfolio?faces-redirect=true";
	}
	
	public List<Podcast> getPodcasts() {
		System.out.println("Tentou atualizar o podcast GET");
		return podcasts;
	}

	public void setPodcasts(List<Podcast> podcasts) {
		System.out.println("Tentou atualizar o podcast SET");
		System.out.println(podcasts.size());
		this.podcasts = podcasts;
	}
	public void setService(SessionSearchSemantic service){
		System.out.println("Entrou Podcast SErvice");
		this.service = service;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
