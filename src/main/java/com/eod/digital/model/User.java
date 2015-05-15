package com.eod.digital.model;

public class User {
  private final String email;
  private final String username;
  private final String firstName;
  private final String lastName;
  private final String password;

  private User(Builder builder) {
      this.email = builder.email;
      this.username = builder.username;
      this.firstName = builder.firstName;
      this.lastName = builder.lastName;
      this.password = builder.password;
  }

  public String getEmail() {
      return email;
  }
  public String getUsername() {
      return username;
  }
  public String getFirstName() {
      return firstName;
  }
  public String getLastName() {
      return lastName;
  }
  public String getPassword() {
      return password;
  }
  
  public static class Builder() {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String password;

    public Builder() {}
    
    public Builder(User user) {
        this.email = user.email;
        this.username = user.username;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.password = user.password;
    }
    
    public Builder setEmail(String email) {
        this.email = email;
        return this;
    }
    public Builder setUsername(String username) {
        this.username = username;
        return this;
    }
    public Builder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    public Builder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public Builder setPassword(String password) {
        this.password = password;
        return this;
    }
    
    public User build() {
        return new User(this);
    }
  }
}
