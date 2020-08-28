package org.gasan.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gasan.domain.CGVInfoDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/*")
//@RestController
@Log4j
@Controller
@AllArgsConstructor
public class testEx {
	
	@GetMapping("/main") // 메인 리스트 크롤링
	public void got5(Model model) throws IOException {

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
//	for(int i=0;i<ranks.size();i++) {
//		System.out.println(ranks.get(i));
//		System.out.println(imgs.get(i));
//		System.out.println(movieAges.get(i));
//		System.out.println(movieTitles.get(i));
//		System.out.println(movieRates.get(i));
//		System.out.println(movieOpenDates.get(i));
//		System.out.println(likes.get(i));
//	}
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
//		System.out.println(cgvInfoDTO);
	}
	model.addAttribute("list",list);
	
//  //롯데시네마는 왜 안되지?
//	System.out.println("실행");
//	String imgUrl = "https://www.lottecinema.co.kr/NLCHS/";
//	Document doc = Jsoup.connect(imgUrl).get();
//	/* Elements */
//	Elements posters = doc.select("span.movi_info_txt");
//	System.out.println(posters);
//	Elements ranks = doc.select(".num_info");
//	Elements movieAges = doc.select(".ic_grade gr_12");
//	Elements movieTitles = doc.select("tit_info");
//	Elements movieRates = doc.select(".rate_info");
//	Elements movieRates2 = doc.select(".rate_info em");
//	Elements likes = doc.select(".remain_info");
//	for(int i=0;i<posters.size();i++) {
//		System.out.println(posters.get(i));
//		System.out.println(ranks.get(i));
//		System.out.println(movieAges.get(i));
//		System.out.println(movieTitles.get(i));
//		System.out.println(movieRates.get(i));
//		System.out.println(movieRates2.get(i));
//		System.out.println(likes.get(i));
//	}
//	List<LotteInfoDTO> list = new ArrayList<LotteInfoDTO>();
//	for(int i=0;i<ranks.size();i++) {
//		String poster = posters.get(i).text();
//		String rank = ranks.get(i).text();
//		String movieAge = movieAges.get(i).text();
//		String movieTitle = movieTitles.get(i).text();
//		String movieRate = movieRates.get(i).text();
//		String movieRate2 = movieRates2.get(i).text();
//		String like = likes.get(i).text();
//		
//		LotteInfoDTO lotteInfoDTO = new LotteInfoDTO(poster,rank,movieAge,movieTitle,movieRate,movieRate2,like);
//		list.add(lotteInfoDTO);
//		System.out.println(lotteInfoDTO);
//	}
//	model.addAttribute("list",list);
		

	
	}
	
	@GetMapping("/board/result4") // 영화인 상세정보에서 필모 클릭시 영화 상세정보
	public void got4(@RequestParam(value = "mNm") String m,
			@RequestParam(value = "img") String img, @RequestParam(value="movieCd") String movieCd,
			@RequestParam(value= "dNm") String d,Model model) throws Exception{

		String json;
		BufferedReader br;
		URL url;
		HttpURLConnection conn;
		String protocol = "GET";
		String address = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=ffe7902a1d87b65336b40d9ffe1de421&movieCd="+movieCd;
		
		url = new URL(address);
		conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod(protocol);
		br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		JSONParser parser = new JSONParser();
		JSONObject obj = new JSONObject();
		
		JSONArray array = new JSONArray();
		
		json = br.readLine();
		obj = (JSONObject) parser.parse(json);
		System.out.println(obj);
		obj = (JSONObject) obj.get("movieInfoResult");
		System.out.println(obj);
		obj = (JSONObject) obj.get("movieInfo");
		System.out.println(obj);

		System.out.println("==========여기꺼 쓰자===========");

		
		System.out.println(obj.get("showTm"));
		System.out.println(obj.get("movieNmEn"));
//		obj = (JSONObject) array.get(0);
//		System.out.println(obj.get("watchGradeNm"));
		array = (JSONArray) obj.get("audits"); // "audits":[] 일 경우 에러..
		Map<String, Object> map = testEx.getMapFromJsonObject( ( JSONObject ) array.get(0) );
		String gr = (String) map.get("watchGradeNm");
		model.addAttribute("mNm",m);
		model.addAttribute("open",obj.get("prdtStatNm"));
		model.addAttribute("year",obj.get("prdtYear"));
		model.addAttribute("openDt",obj.get("openDt"));
		model.addAttribute("genre",obj.get("genreAlt"));
		model.addAttribute("showTm",obj.get("showTm"));
		model.addAttribute("grade",gr);
		model.addAttribute("mNmEn",obj.get("movieNmEn"));
		array = (JSONArray) obj.get("actors");
		System.out.println(array);
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		for(int i=0;i<array.size();i++) {
			Map<String, Object> map2 = testEx.getMapFromJsonObject((JSONObject) array.get(i));
			list2.add(map2);
		}
		model.addAttribute("list",list2);
//		System.out.println(list);
		
		// 줄거리 받아오기
		Object en;
		en = obj.get("movieNmEn");
		System.out.println(en);
		String en2 = en.toString();
		System.out.println(en2);
		String text = null;
        try {
            text = URLEncoder.encode(m, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
        String text2 = null;
        try {
            text2 = URLEncoder.encode(d, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
        String text3 = null;
        try {
            text3 = URLEncoder.encode(en2, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
        //System.out.println(text);
		address = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json.jsp?collection=kmdb_new&ServiceKey=FLEG4U4H7XDMM834PTOF&title="+text+"&actor="+text2;
		// 끝에 director 일 시 director에 넣고싶은데 어떻게??
//		url = new URL(address);
//		conn = (HttpURLConnection)url.openConnection();
//		conn.setRequestMethod("GET");
//		br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		json = br.readLine();
		
		url = new URL(address); 
		conn = (HttpURLConnection) url.openConnection(); 
		conn.setRequestMethod("GET"); 
		conn.setRequestProperty("Content-type", "application/json"); 
		System.out.println("Response code: " + conn.getResponseCode()); 
		BufferedReader rd; 
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) { 
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			} else { 
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream())); 
			} 
//		System.out.println(rd);
//		json = rd.readLine();
//		System.out.println(json);
//		parser = new JSONParser();
//		obj = new JSONObject();
//		obj = (JSONObject) parser.parse(json);
//		System.out.println(obj);
//		obj = (JSONObject) obj.get("movieListResult");
//		array = (JSONArray) obj.get("movieList");
		StringBuilder sb = new StringBuilder(); 
		String line; 
		while ((line = rd.readLine()) != null) { 
			sb.append(line); 
		} 
		rd.close(); 
		conn.disconnect(); 
		json = sb.toString();
		obj = (JSONObject) parser.parse(json); 
//		if(Integer.parseInt(obj.get("TotalCount").toString()) == 0) { // 제목과 감독으로 검색 시 검색결과 없으면 제목으로만 검색
//			address = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json.jsp?collection=kmdb_new&ServiceKey=FLEG4U4H7XDMM834PTOF&title="+text;
//			url = new URL(address);
//			conn = (HttpURLConnection)url.openConnection();
//			conn.setRequestMethod(protocol);
//			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			json = br.readLine();
//			System.out.println("totCnt 0일경우만 이거 실행");
//		}
		System.out.println(obj);
		System.out.println("==========");
		array = (JSONArray) obj.get("Data");
		System.out.println(array);
		json = array.get(0).toString();
		obj = (JSONObject) parser.parse(json);
		array = (JSONArray) obj.get("Result");
		json = array.get(0).toString();
		obj = (JSONObject) parser.parse(json);
		String plot = (String) obj.get("plot");
		
		System.out.println("줄거리: "+plot);
		model.addAttribute("plot",plot);
		model.addAttribute("img",obj.get("posters"));
		
		
	}
	
	@GetMapping("/board/result3") //영화 상세정보
	public void got3(@RequestParam(value = "mNm") String m,
			@RequestParam(value = "img") String img,
			@RequestParam(value= "dNm") String d,Model model) throws Exception{

		String json;
		String address = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=ffe7902a1d87b65336b40d9ffe1de421&movieNm="+m+"&directorNm="+d;
		BufferedReader br;
		URL url;
		HttpURLConnection conn;
		String protocol = "GET";
		
		url = new URL(address);
		conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod(protocol);
		br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		json = br.readLine();
		System.out.println(json);
		JSONParser parser = new JSONParser();
		JSONObject obj = new JSONObject();
		obj = (JSONObject) parser.parse(json);
		obj = (JSONObject) obj.get("movieListResult");
		System.out.println(obj);
		System.out.println(obj.get("movieList"));
		if(Integer.parseInt(obj.get("totCnt").toString()) == 0) { // 제목과 감독으로 검색 시 검색결과 없으면 제목으로만 검색
			address = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=ffe7902a1d87b65336b40d9ffe1de421&movieNm="+m;
			url = new URL(address);
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod(protocol);
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			json = br.readLine();
			System.out.println("totCnt 0일경우만 이거 실행");
		}
		obj = (JSONObject) parser.parse(json);
		System.out.println(obj);
		obj = (JSONObject) obj.get("movieListResult");
		JSONArray array = (JSONArray) obj.get("movieList");
		System.out.println(array);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String movieCd = null;
		for(int i = 0; i< array.size(); i++) {
			Map<String, Object> map = testEx.getMapFromJsonObject( ( JSONObject ) array.get(i) );
            //list.add( map );
			movieCd = (String) map.get("movieCd");
		}
		System.out.println(movieCd);		
		// movieCd 습득
		address = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=ffe7902a1d87b65336b40d9ffe1de421&movieCd="+movieCd;
		
		url = new URL(address);
		conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod(protocol);
		br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		json = br.readLine();
		obj = (JSONObject) parser.parse(json);
		obj = (JSONObject) obj.get("movieInfoResult");
		obj = (JSONObject) obj.get("movieInfo");
		System.out.println(obj);
		System.out.println(array);
		System.out.println("==========여기꺼 쓰자===========");
		JSONObject obj2 = new JSONObject();
		obj2 = (JSONObject) array.get(0);
		
		System.out.println(obj.get("showTm"));
		System.out.println(obj.get("movieNmEn"));
//		obj = (JSONObject) array.get(0);
//		System.out.println(obj.get("watchGradeNm"));
		array = (JSONArray) obj.get("audits"); // audits 없을 시 에러나는거 어떻게하지??
		Map<String, Object> map = testEx.getMapFromJsonObject( ( JSONObject ) array.get(0) );
		String gr = (String) map.get("watchGradeNm");
		model.addAttribute("mNm",m);
		model.addAttribute("img",img);
		model.addAttribute("open",obj2.get("prdtStatNm"));
		model.addAttribute("year",obj2.get("prdtYear"));
		model.addAttribute("openDt",obj2.get("openDt"));
		model.addAttribute("genre",obj2.get("genreAlt"));
		model.addAttribute("showTm",obj.get("showTm"));
		model.addAttribute("grade",gr);
		model.addAttribute("mNmEn",obj.get("movieNmEn"));
		array = (JSONArray) obj.get("actors");
		System.out.println(array);
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		for(int i=0;i<array.size();i++) {
			Map<String, Object> map2 = testEx.getMapFromJsonObject((JSONObject) array.get(i));
			list2.add(map2);
		}
		model.addAttribute("list",list2);
//		System.out.println(list);
		
		// 줄거리 받아오기
		Object en;
		en = obj.get("movieNmEn");
		System.out.println(en);
		String en2 = en.toString();
		System.out.println(en2);
		String text = null;
        try {
            text = URLEncoder.encode(m, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
        String text2 = null;
        try {
            text2 = URLEncoder.encode(d, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
        String text3 = null;
        try {
            text3 = URLEncoder.encode(en2, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
        //System.out.println(text);
		address = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json.jsp?collection=kmdb_new&ServiceKey=FLEG4U4H7XDMM834PTOF&title="+text+"&query="+text2;

//		url = new URL(address);
//		conn = (HttpURLConnection)url.openConnection();
//		conn.setRequestMethod("GET");
//		br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		json = br.readLine();
		
		url = new URL(address); 
		conn = (HttpURLConnection) url.openConnection(); 
		conn.setRequestMethod("GET"); 
		conn.setRequestProperty("Content-type", "application/json"); 
		System.out.println("Response code: " + conn.getResponseCode()); 
		BufferedReader rd; 
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) { 
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			} else { 
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream())); 
			} 
//		System.out.println(rd);
//		json = rd.readLine();
//		System.out.println(json);
//		parser = new JSONParser();
//		obj = new JSONObject();
//		obj = (JSONObject) parser.parse(json);
//		System.out.println(obj);
//		obj = (JSONObject) obj.get("movieListResult");
//		array = (JSONArray) obj.get("movieList");
		StringBuilder sb = new StringBuilder(); 
		String line; 
		while ((line = rd.readLine()) != null) { 
			sb.append(line); 
		} 
		rd.close(); 
		conn.disconnect(); 
		json = sb.toString();
		obj = (JSONObject) parser.parse(json); 
		System.out.println(obj);
		System.out.println("==========");
		array = (JSONArray) obj.get("Data");
		System.out.println(array);
		json = array.get(0).toString();
		obj = (JSONObject) parser.parse(json);
		array = (JSONArray) obj.get("Result");
		json = array.get(0).toString();
		obj = (JSONObject) parser.parse(json);
		String plot = (String) obj.get("plot");
		
		System.out.println("줄거리: "+plot);
		model.addAttribute("plot",plot);
		
    }
	
	@GetMapping("/board/result2") // 영화인 상세정보
	public void got2(@RequestParam(value = "img") String img, 
			@RequestParam(value = "Nm") String n,@RequestParam(value= "title") String t,
			 Model model) throws Exception{
		model.addAttribute("img",img);
		String json;
		String address = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/people/searchPeopleList.json?key=ffe7902a1d87b65336b40d9ffe1de421&peopleNm="+n+"&filmoNames="+t;
		BufferedReader br;
		URL url;
		HttpURLConnection conn;
		String protocol = "GET";

		url = new URL(address);
		conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod(protocol);
		br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		json = br.readLine();
		System.out.println(json);
		JSONParser parser = new JSONParser();
		JSONObject obj = new JSONObject();
		obj = (JSONObject) parser.parse(json);
		obj = (JSONObject) obj.get("peopleListResult");
		// 여기까지는 영화인 목록
		JSONArray array = (JSONArray) obj.get("peopleList");
		System.out.println(array.size());
		System.out.println(array);
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String peopleCd = null;
		for(int i = 0; i< array.size(); i++) {
			Map<String, Object> map = testEx.getMapFromJsonObject( ( JSONObject ) array.get(i) );
            //list.add( map );
			peopleCd = (String) map.get("peopleCd");
		}
		System.out.println(peopleCd);
		// peopleCd 로 영화인 상세정보 빼온다
		address = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/people/searchPeopleInfo.json?key=ffe7902a1d87b65336b40d9ffe1de421&peopleCd="+peopleCd;
		
		url = new URL(address);
		conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod(protocol);
		br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		json = br.readLine();
		System.out.println(json);
		obj = (JSONObject) parser.parse(json);
		obj = (JSONObject) obj.get("peopleInfoResult");
		obj = (JSONObject) obj.get("peopleInfo");
		System.out.println(obj);
		model.addAttribute("n",n);
		model.addAttribute("t",t);
		model.addAttribute("Nm",obj.get("peopleNm"));
		model.addAttribute("NmEn",obj.get("peopleNmEn"));
		model.addAttribute("RoleNm",obj.get("repRoleNm"));
		model.addAttribute("sex",obj.get("sex"));
		array = (JSONArray) obj.get("filmos");
		System.out.println(array);
		System.out.println("=========여기서 수정=======");
//		   HashSet<String,Object> hs = new HashSet<String>();
//
//		   for (int i = 0; i<xmlList.size(); i++){
//				List<뭔지모를 클래스> ctry = xmlList.get(i);
//
//				for(int j = 0; j<ctry.size(); j++){
//					hs.add(ctry.get(i).getCountry);
//
//				}
//
//		   }
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(int i=0;i<array.size();i++) {
			Map<String, Object> map = testEx.getMapFromJsonObject((JSONObject) array.get(i));
			list.add(map);
			
//			if(list.size() == 0) {
//				list.add(map);
//				continue;
//			}
//			if(!list.get(i).containsValue(map.get("movieCd"))) {
//				list.add(map);
//			}
		}
//		 감독 클릭 시 영화제목 중복 제거해보자
//		for(int i=0;i<list.size();i++) {
//			if(list.get(i).get("movieCd").equals(list.get(i+1).get("movieCd"))){
//				list.remove(i);
//			}
//		}
		conn.disconnect();
		model.addAttribute("list",list);
		System.out.println(list);
		
		// 영화인 이미지 크롤링
		String text = null;
        try {
            text = URLEncoder.encode(n, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
		System.out.println(text);
		String imgUrl = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query="+text;
		Document doc = Jsoup.connect(imgUrl).get();
		Elements hot = doc.select("div.profile_wrap div.big_thumb img"); 
		System.out.println(hot.toString());
		
		model.addAttribute("imgUrl",hot.toString());
//		Document doc = Jsoup.connect("http://news.naver.com/").get();
//
//		Element hotNews = doc.select("ul.mlist2").get(0);
//
//		System.out.println(hotNews.toString());
				
    }
	
	@GetMapping("/board/result")
	public void got(@RequestParam(value = "sear") String a,Model model) throws Exception{

		String clientId = "15Ewh_t3qRDvYGjEGahC"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "Ho3qBDALMA"; //애플리케이션 클라이언트 시크릿값"


        String text = null;
        try {
            text = URLEncoder.encode(a, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text+"&display=100";    // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);

//        System.out.println(responseBody);
        
		//WebConnection wc = new WebConnection();
//		System.out.println(wc.json);
		JSONParser parser = new JSONParser();
		JSONObject obj = new JSONObject();
		obj = (JSONObject) parser.parse(responseBody);
		System.out.println(obj);
		JSONArray array = (JSONArray) obj.get("items");
		System.out.println(array);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//resultVO rv = new resultVO();
		        if( array != null )
		        {
		            int jsonSize = array.size();
		            for( int i = 0; i < jsonSize; i++ )
		            {
		            	
		                Map<String, Object> map = testEx.getMapFromJsonObject( ( JSONObject ) array.get(i) );
		                list.add( map );
		                
		            }
		        }
		        System.out.println(list);
//		        for(int i=0; i<llist.size();i++) {
//		        	System.out.println(llist.get(i));
//		        }
		model.addAttribute("list",list);
		model.addAttribute("tot",obj.get("total"));
		        //for(int i = 0; i<array.size(); i++) {
	        

	        //System.out.println(list);			
			//JSONObject movie = (JSONObject) array.get(i);
			//System.out.println(i+1+"번째 영화 : "+movie.get("title")+","+movie.get("director"));
			//vo.setActor((String)movie.get("actor"));
			//System.out.println(movie.get("image"));
			//model.addAttribute("list",lv);//모델에 resultVO 형태든 뭐든 원하는 리스트 보내서 result.jsp에서 출력하기 해라
		
    }
	
	

    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getMapFromJsonObject( JSONObject jsonObj )
    {
        Map<String, Object> map = null;
        
        try {
            
            map = new ObjectMapper().readValue(jsonObj.toJSONString(), Map.class) ;
            
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return map;
    }
}
