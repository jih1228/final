package org.gasan.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.gasan.domain.CGVInfoDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws IOException {

		System.out.println("실행");
		String imgUrl = "http://www.cgv.co.kr/movies/";
		Document doc = Jsoup.connect(imgUrl).get();
		/* Elements */
		Elements ranks = doc.select(".rank");
		Elements imgs = doc.select(".thumb-image > img");
		Elements movieAges = doc.select(".ico-grade");
		Elements movieTitles = doc.select("div.box-contents strong.title");
		Elements movieRates = doc.select(".percent span");
		Elements movieOpenDates = doc.select(".txt-info strong");
		Elements likes = doc.select(".link-reservation");
		for(int i=0;i<ranks.size();i++) {
			System.out.println(ranks.get(i));
			System.out.println(imgs.get(i));
			System.out.println(movieAges.get(i));
			System.out.println(movieTitles.get(i));
			System.out.println(movieRates.get(i));
			System.out.println(movieOpenDates.get(i));
			System.out.println(likes.get(i));
		}
		List<CGVInfoDTO> list = new ArrayList<CGVInfoDTO>();
		for(int i=0;i<ranks.size();i++) {
			String rank = ranks.get(i).text();
			String img = imgs.get(i).attr("src");
			String movieAge = movieAges.get(i).text();
			String str = movieAge.substring(0,2);
			String movieTitle = movieTitles.get(i).text();
			String movieRate = movieRates.get(i).text();
			String movieOpenDate = movieOpenDates.get(i).text();
			String like = likes.get(i).text();
			
			CGVInfoDTO cgvInfoDTO = new CGVInfoDTO(rank,img,str,movieTitle,movieRate,movieOpenDate,like);
			list.add(cgvInfoDTO);
			System.out.println(cgvInfoDTO);
		}
		model.addAttribute("list",list);
			
		return "main";
		
		}
	
}
