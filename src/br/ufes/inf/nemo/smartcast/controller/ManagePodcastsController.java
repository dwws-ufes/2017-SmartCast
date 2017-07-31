package br.ufes.inf.nemo.smartcast.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;

import br.ufes.inf.nemo.smartcast.application.ManagePodcastsService;
import br.ufes.inf.nemo.smartcast.domain.Podcast;

@ManagedBean
@Named
public class ManagePodcastsController {
	
	private List<Podcast> podcasts = new ArrayList<>();
	
	private String searchString;
	private String searchStringSemantic;
	
	@EJB ManagePodcastsService managePodcastService;
	
	
//	public String getImageUrl(Podcast podcast){
//		return podcast.getTag("image").getTag("url").getValue().get(0);
//	}
//	
//	public String getImageLink(Podcast podcast){
//		return podcast.getTag("image").getTag("link").getValue().get(0);
//	}
	
	
	public void setSearchString(String searchString){
		System.out.println("SEARCH");
		System.out.println(searchString);
		this.podcasts = managePodcastService.search(searchString);
		this.searchString = searchString;
	}
	
	public String getSearchString(){
		System.out.println("GET");
		System.out.println(searchString);
		return searchString;
	}
	public String reloadPodcast(){
		if(searchString == null || searchString.equals("")){
			System.out.println("Entrou no vazio");
			this.podcasts = managePodcastService.getSome();
		}else{
			System.out.println("ReloadPodcast");
			this.podcasts = managePodcastService.search(searchString);
		}
		return "/resultSemantic/index.faces#portfolio?faces-redirect=true";
	}	
	public String searchPodcastSemantic(){
		//Podcast Semantic
		System.out.println("Podcast Semantic");
		System.out.println(getSearchStringSemantic());
		
		return null;
	}
	
	
	public List<Podcast> getPodcasts(){
		System.out.println("Retornou lista podcast");
		System.out.println(podcasts);
		return podcasts;
	}
	
	

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
	
	
}
