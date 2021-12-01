package com.ctrip.apollo.portal.controller;

import com.ctrip.apollo.core.enums.Env;
import com.ctrip.apollo.portal.PortalSettings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/envs")
public class PortalEnvController {

  @Autowired
  private PortalSettings portalSettings;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<Env> envs(){
    return portalSettings.getEnvs();
  }

}
