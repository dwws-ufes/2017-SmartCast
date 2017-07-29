package br.ufes.inf.nemo.smartcast.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.ufes.inf.nemo.smartcast.application.PodcastService;
import br.ufes.inf.nemo.smartcast.domain.Podcast;

@ManagedBean
@ViewScoped
public class DataScrollerView {
	private List<Podcast> podcasts = new ArrayList<>();
	
	
	@ManagedProperty("#{podcastService}")
    private PodcastService service;
	
	
	@PostConstruct
    public void init() {
		podcasts = service.podcasts();
    }
	
	public List<Podcast> getPodcasts(){
		System.out.println("Retornou lista podcast");
		System.out.println(podcasts);
		return podcasts;
	}
	
	public void setService(PodcastService service){
		System.out.println("Entrou Podcast SErvice");
		this.service = service;
	}
}
