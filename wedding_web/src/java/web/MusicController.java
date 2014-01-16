package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/music")
public class MusicController 
{
    @RequestMapping(method=RequestMethod.GET)
    public String getHome()
    {
        return "music";
    }
}
