package web;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RsvpController 
{
    @RequestMapping(value="/rsvp", method=RequestMethod.GET)
    public String displayCodeInput()
    {
        return "rsvp";
    }
    
    @RequestMapping(value="/rsvp_select", method=RequestMethod.GET)
    public ModelAndView displaySelection(HttpServletRequest request)
    {
        String code = request.getParameter("code");
        ModelAndView mav = null;
        
        Invite i = lookupInvite(code);
        
        if (i == null) 
        {
            mav = new ModelAndView("rsvp");
            mav.addObject("errorMessage", "That code is invalid. Please try again.");
        }
        else
        {
            mav = new ModelAndView("rsvp_select");
            mav.addObject("invite", i);
        }

        return mav;
    }
    
    @RequestMapping(value="/rsvp_confirm", method=RequestMethod.POST)
    public ModelAndView confirmRsvp(@ModelAttribute("invite") Invite i)
    {
        StringBuilder sb = new StringBuilder();
        Boolean isGoing = null;
        ModelAndView mav = null;
        
        for (Person p : i.getPeople()) 
        {
            String val;
            
            if (p.isGoing() && p.isNotGoing()) {
                mav = new ModelAndView("rsvp_select");
                mav.addObject("errorMessage", "You can't be going and not going at the same time!");
                return mav;
            }
            
            if (!p.isGoing() && !p.isNotGoing()) {
                mav = new ModelAndView("rsvp_select");
                mav.addObject("errorMessage", "Are you going or aren't you? Make up your mind :)");
                return mav;
            }
            
            if (p.isGoing()) { isGoing = true; };
            if (p.isNotGoing()) { isGoing = false; };
            
            if (isGoing == null) {
                mav = new ModelAndView("rsvp_select");
                mav.addObject("invite", i);
                mav.addObject("error", "Not all invitees are filled in.");
                return mav;
            }
            
            val = isGoing ? "yes" : "no";
            
            sb.append(p.getName());
            sb.append(":");
            sb.append(val);
            sb.append(System.getProperty("line.separator"));
        }
        
        mav = new ModelAndView("rsvp_confirm");
        
        SendRsvpEmail(sb.toString());
        
        return mav;
    }
    
    private Invite lookupInvite(String code)
    {
        Invite [] invites = new Invite [] {
            new Invite("0", new Person [] { 
                new Person("Mr Claus"),
                new Person("Mrs Claus")
        })};

        for (Invite i : invites) {
            if (i.getCode().equalsIgnoreCase(code)) {
                return i;
            }
        }
        
        return null;
    }
    
    private void SendRsvpEmail(String text)
    {
        // Recipient's email ID needs to be mentioned.
        String to = "leaheandrews@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "steve@stepheninkpen.com";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try
        {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                                     new InternetAddress(to));

            message.addRecipient(Message.RecipientType.TO,
                                     new InternetAddress("steve@stepheninkpen.com"));
            
            // Set Subject: header field
            message.setSubject("Wedding RSVP");

            // Now set the actual message
            message.setText(text);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } 
        catch (MessagingException mex)
        {
            mex.printStackTrace();
        }
    }
}
