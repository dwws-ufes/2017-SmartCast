package br.ufes.inf.nemo.smartcast.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.ufes.inf.nemo.smartcast.application.PodcastService;
import br.ufes.inf.nemo.smartcast.application.PodcastServiceResult;
import br.ufes.inf.nemo.smartcast.domain.Podcast;

@ManagedBean
@ViewScoped
public class DataScrollerViewResultSearch {
	private Podcast podcasts = new Podcast();
	
	
	@ManagedProperty("#{podcastServiceResult}")
    private PodcastServiceResult service;
	
	
	@PostConstruct
    public void init() {
		podcasts = service.podcasts();
    }
	
	public Podcast getPodcasts(){
		System.out.println("Retornou lista podcast");
		System.out.println(podcasts);
		return podcasts;
	}
	
	public void setService(PodcastServiceResult service){
		System.out.println("Entrou Podcast SErvice");
		this.service = service;
	}
}
