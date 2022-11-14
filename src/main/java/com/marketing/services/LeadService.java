package com.marketing.services;

import java.util.List;

import com.marketing.entities.Lead;

public interface LeadService {
   public void saveLead(Lead lead);

   public List<Lead> getLeads();
   public Lead findOneLead(long id);
   public void deleteOneLead(long id);
}
