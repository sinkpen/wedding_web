package web;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
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
        
        String path = request.getServletContext().getRealPath("/guest_list_codes.txt");
        List<Invite> list = loadInvites(path);
        
        Invite i = lookupInvite(list, code);
        
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
        ModelAndView mav = null;
        
        for (Person p : i.getPeople()) 
        {
            String val;
            
            val = p.isGoing() ? "yes" : "no";
            
            sb.append(p.getName());
            sb.append(":");
            sb.append(val);
            sb.append(System.getProperty("line.separator"));
        }
        
        sb.append(System.getProperty("line.separator"));
        sb.append("Requests:");
        sb.append(i.getRequests());
        
        try {
            SendRsvpEmail(sb.toString());
            mav = new ModelAndView("rsvp_confirm");
        } catch (MessagingException ex) {
            mav = new ModelAndView("rsvp_select");
            mav.addObject("errorMessage", "There was a problem RSVPing. Please try again.");
        }
        
        return mav;
    }
    
    private Invite lookupInvite(List<Invite> list, String code)
    {
        for (Invite i : list) {
            if (i.getCode().equalsIgnoreCase(code)) {
                return i;
            }
        }
        
        return null;
    }
    
    List<Invite> loadInvites(String filePath)
    {
        Path p = FileSystems.getDefault().getPath(filePath);
        List<String> lines;
        
        try
        {
            lines = Files.readAllLines(p, Charset.defaultCharset());
        }
        catch (IOException ex)
        {
            return new Vector<>();
        }
        
        List<Invite> invites = new Vector<>();
        
        for (String line : lines)
        {
            String code = "";
            String [] people = new String[0];
            String [] lineParts = line.split("\\|");
            
            if (lineParts.length > 0)
            {
                code = lineParts[0].trim();
            }
            
            if (lineParts.length > 1)
            {
                people = lineParts[1].split(",");
            }
            
            List<Person> list = new Vector<>();
            for (String person : people)
            {
                list.add(new Person(person.trim()));
            }
            
            Invite i = new Invite(code, list);
            invites.add(i);
        }
        
        return invites;
    }
    
    private void SendRsvpEmail(String text) throws MessagingException
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
}
