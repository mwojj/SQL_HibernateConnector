package com.mwoj.StudyTests.controller;

import com.mwoj.StudyTests.domain.repository.QuestionRepository;
import com.mwoj.StudyTests.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody
    String addNewQuestion (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Question n = new Question();
        n.setName(name);
        n.setEmail(email);
        questionRepository.save(n);
        return "saved";
    }

    @RequestMapping("/added")
    public String getAdded(ModelMap model, @RequestParam("name") String name, @RequestParam("email") String email){

        Question q = new Question();
        q.setName(name);
        q.setEmail(email);
        questionRepository.save(q);
        model.addAttribute("name", q.getName());
        model.addAttribute("email", email);
        return "save";
}

    @GetMapping(path="/all")
    public String showAll(@ModelAttribute Question question, ModelMap modelMap){
        modelMap.addAttribute("question", questionRepository.findAll());
        return "all";
    }

    @GetMapping("/search")
    public String search(){
        return "search";
    }

    @GetMapping("/result")
    public String searchByName(String name, ModelMap modelMap){
        modelMap.addAttribute("results", questionRepository.findByName(name));
        return "result";
    }

    @RequestMapping("/remove")
    public String remove(ModelMap model, @RequestParam("id") Long id){
        Question q = new Question();
        model.addAttribute("id", id);
        questionRepository.deleteById(id);
        return "remov";
    }

}



