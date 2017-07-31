package br.ufes.inf.nemo.smartcast.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.RSS;
import org.apache.jena.vocabulary.VCARD4;

//import br.ufes.inf.nemo.smartcast.domain.Episode;
import br.ufes.inf.nemo.smartcast.domain.Podcast;
import br.ufes.inf.nemo.smartcast.persistence.PodcastDAO;



@WebServlet(urlPatterns = { "/data/podcasts" })

public class ListPodcastsInRdfServlet extends HttpServlet{
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	@EJB
	private PodcastDAO podcastDAO;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {resp.setContentType("text/xml");
	
	List<Podcast> podcasts = podcastDAO.retrieveAll();
	
	//List<Episode> episodes = new ArrayList<>();
	
	Model model = ModelFactory.createDefaultModel();
	
	String myNS = "http://localhost:8080/Smartcast/data/Podcasts/";
	
	String grNS = "http://purl.org/media#Collection";
	model.setNsPrefix("gr", grNS);
	
	Property grtitle = ResourceFactory.createProperty(grNS + "title");
	Property grlink = ResourceFactory.createProperty(grNS+ "link");
	Property grurl = ResourceFactory.createProperty(grNS + "url");
	Property grdescription = ResourceFactory.createProperty(grNS + "description");
	Property grcategory = ResourceFactory.createProperty(grNS + "category");
	Property grimage = ResourceFactory.createProperty(grNS + "image");
	
	for (Podcast podcast : podcasts) {
	//	episodes = podcast.getEpisodes();
		model.createResource(myNS + podcast.getId())	
			.addProperty(DCTerms.title, podcast.getTitle())
			.addProperty(DCTerms.description, podcast.getDescription())
			.addProperty(VCARD4.category, podcast.getCategory())
			.addProperty(RSS.link, podcast.getLink())
			.addProperty(RSS.image, podcast.getImage())
			.addProperty(RSS.url, podcast.getUrl());
			
							
					
	//	.addLiteral(grurl, 
	//ResourceFactory.createTypedLiteral(df.format(podcast.getUrl()),
	//XSDDatatype.XSDanyURI))
	
	//	.addProperty(grhasPriceSpecification, model.createResource()
	//		.addProperty(RDF.uri, grPriceSpecification)
	//		.addLiteral(grhasCurrencyValue, pack.getPrice().floatValue()));
									
			
	}
	
	
	try (PrintWriter out = resp.getWriter()) {
		model.write(out, "RDF/XML");
	}
	
	
	}
}
