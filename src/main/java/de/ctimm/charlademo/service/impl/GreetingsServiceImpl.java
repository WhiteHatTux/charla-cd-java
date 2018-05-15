package de.ctimm.charlademo.service.impl;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.UnknownHostException;
import org.springframework.stereotype.Service;
import de.ctimm.charlademo.service.GreetingService;

/**
 * @author Christopher Timm <christopher.timm@beskgroup.com> on 15.05.18
 */
@Service
public class GreetingsServiceImpl
  implements GreetingService {



  @Override
  public String calcHostName(){
    try {
      return InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
      return "could not calculate";
    }
  }

}
