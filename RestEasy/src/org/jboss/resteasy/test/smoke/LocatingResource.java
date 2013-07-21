package org.jboss.resteasy.test.smoke;

import javax.ws.rs.Path;

public class LocatingResource
{
  @Path("locating")
  public SimpleResource getLocating()
  {
    System.out.println("LOCATING...");
    return new SimpleResource();
  }
}