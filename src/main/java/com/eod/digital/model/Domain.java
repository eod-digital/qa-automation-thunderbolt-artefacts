package com.eod.digital.model;

public class Domain {
  private final String name;
  private final String url;
  private final String token;
  private final String description;
  
  private Domain(Builder builder) {
      this.name = builder.name;
      this.url = builder.url;
      this.token = builder.token;
      this.description = builder.description;
  }
}
