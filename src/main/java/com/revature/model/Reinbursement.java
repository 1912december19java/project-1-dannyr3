package com.revature.model;

public class Reinbursement {

  private Integer id;
  private Integer emp_Id;
  private Integer manager_Id;
  private String datetime;
  private String type;
  private String status;
  private Double cost;
  private String requestor_comments;
  private String approver_comments;
  private String image_location;
  
  public Reinbursement() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Reinbursement(Integer id, Integer emp_Id, Integer manager_Id, String datetime, String type,
      String status, Double cost, String requestor_comments, String approver_comments,
      String image_location) {
    super();
    this.id = id;
    this.emp_Id = emp_Id;
    this.manager_Id = manager_Id;
    this.datetime = datetime;
    this.type = type;
    this.status = status;
    this.cost = cost;
    this.requestor_comments = requestor_comments;
    this.approver_comments = approver_comments;
    this.image_location = image_location;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getEmp_Id() {
    return emp_Id;
  }

  public void setEmp_Id(Integer emp_Id) {
    this.emp_Id = emp_Id;
  }

  public Integer getManager_Id() {
    return manager_Id;
  }

  public void setManager_Id(Integer manager_Id) {
    this.manager_Id = manager_Id;
  }

  public String getDatetime() {
    return datetime;
  }

  public void setDatetime(String datetime) {
    this.datetime = datetime;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Double getCost() {
    return cost;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }

  public String getRequestor_comments() {
    return requestor_comments;
  }

  public void setRequestor_comments(String requestor_comments) {
    this.requestor_comments = requestor_comments;
  }

  public String getApprover_comments() {
    return approver_comments;
  }

  public void setApprover_comments(String approver_comments) {
    this.approver_comments = approver_comments;
  }

  public String getImage_location() {
    return image_location;
  }

  public void setImage_location(String image_location) {
    this.image_location = image_location;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((approver_comments == null) ? 0 : approver_comments.hashCode());
    result = prime * result + ((cost == null) ? 0 : cost.hashCode());
    result = prime * result + ((datetime == null) ? 0 : datetime.hashCode());
    result = prime * result + ((emp_Id == null) ? 0 : emp_Id.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((image_location == null) ? 0 : image_location.hashCode());
    result = prime * result + ((manager_Id == null) ? 0 : manager_Id.hashCode());
    result = prime * result + ((requestor_comments == null) ? 0 : requestor_comments.hashCode());
    result = prime * result + ((status == null) ? 0 : status.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Reinbursement other = (Reinbursement) obj;
    if (approver_comments == null) {
      if (other.approver_comments != null)
        return false;
    } else if (!approver_comments.equals(other.approver_comments))
      return false;
    if (cost == null) {
      if (other.cost != null)
        return false;
    } else if (!cost.equals(other.cost))
      return false;
    if (datetime == null) {
      if (other.datetime != null)
        return false;
    } else if (!datetime.equals(other.datetime))
      return false;
    if (emp_Id == null) {
      if (other.emp_Id != null)
        return false;
    } else if (!emp_Id.equals(other.emp_Id))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (image_location == null) {
      if (other.image_location != null)
        return false;
    } else if (!image_location.equals(other.image_location))
      return false;
    if (manager_Id == null) {
      if (other.manager_Id != null)
        return false;
    } else if (!manager_Id.equals(other.manager_Id))
      return false;
    if (requestor_comments == null) {
      if (other.requestor_comments != null)
        return false;
    } else if (!requestor_comments.equals(other.requestor_comments))
      return false;
    if (status == null) {
      if (other.status != null)
        return false;
    } else if (!status.equals(other.status))
      return false;
    if (type == null) {
      if (other.type != null)
        return false;
    } else if (!type.equals(other.type))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Reinbursement [id=" + id + ", emp_Id=" + emp_Id + ", manager_Id=" + manager_Id
        + ", datetime=" + datetime + ", type=" + type + ", status=" + status + ", cost=" + cost
        + ", requestor_comments=" + requestor_comments + ", approver_comments=" + approver_comments
        + ", image_location=" + image_location + "]";
  }
  
  
  
  
}
