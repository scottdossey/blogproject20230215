package com.techtalentsouth.techtalentblog.blogposts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogPostController {
	
	//We can't initialize this, we can't construct this...
	//So we are going to ask SpringBoot to magically fill this 
	//value in when our BlogPostController is created.
	//We do this by adding the "@Autowired" annotation.
	@Autowired
	private BlogPostRepository blogPostRepository;
	
	
	/* A request mapping as written here
	 * responds to all HTTP requests.
	 * 
	 * GET/PUT/POST/DELETE/PATCH---all will be processed
	 * by this mapping.  We can specify that we only
	 * want to respond to GET methods
	 * by adding a second parameter labelled method
	 * and saying we respond to RequestMethod.GET
	 *  
	 */
	//@RequestMapping(path="/", method = RequestMethod.GET)
	
	@GetMapping(path="/")
	public String index(Model model) {
		//Since we are using the @Controller
		//annotation rather than the @RestController
		//annotation, the String we are returning 
		//is actually a reference to a html template page.		
		//This model variable is a lot like a HashMap, except all
		//the keys have to be strings.
		BlogPost myBlogPost = new BlogPost();
		myBlogPost.setAuthor("Scott Dossey");
		myBlogPost.setTitle("Something");
		model.addAttribute("blogPost", myBlogPost);
		
		return "blogpost/index";
	}
	
	@PostMapping(path="/")
	public String addNewBlogPost(BlogPost blogPost, Model model) {
		//We can now write the code to save the blogPost 
		//to the database!
		//We need a BlogPostRepository...and its an interface, we can't create
		//it, ONLY SPRINGBOOT can create it.
		blogPostRepository.save(blogPost);
		model.addAttribute("title", blogPost.getTitle());
		model.addAttribute("author", blogPost.getAuthor());
		model.addAttribute("blogEntry", blogPost.getBlogEntry());
		return "blogpost/result";
	}
	
	
}
