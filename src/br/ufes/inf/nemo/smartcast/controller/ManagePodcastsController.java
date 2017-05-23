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
	
	@EJB ManagePodcastsService managePodcastService;
	
	public String getImageUrl(Podcast podcast){
		return podcast.getTag("image").getTag("url").getValue().get(0);
	}
	
	public String getImageLink(Podcast podcast){
		return podcast.getTag("image").getTag("link").getValue().get(0);
	}
	
	public void setSearchString(String searchString){
		this.podcasts = managePodcastService.search(searchString);
		this.searchString = searchString;
	}
	
	public String getSearchString(){
		return searchString;
	}
	public String reloadPodcast(){
		System.out.println(getSearchString());
		if(searchString == null || searchString.equals("")){
			System.out.println("Podcast1");
			this.podcasts = managePodcastService.getSome();
		}else{
			System.out.println("ReloadPodcast");
			this.podcasts = managePodcastService.search(searchString);
		}
		return null;
	}	
	
	public List<Podcast> getPodcasts(){
		return this.podcasts;
	}
	
	public void setPodcast(String busca, int index){
		
	}
	
	
}
