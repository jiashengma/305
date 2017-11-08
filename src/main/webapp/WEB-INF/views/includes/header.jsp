<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>AJA(X) Flight Reservation</title>
    
	<spring:url value="/resources/css/style.css" var="style" />
	<spring:url value="/resources/css/bootstrap.min.css" var="bootstrap" />
	<link type="text/css" rel="stylesheet" href="${style}" />
	<link type="text/css" rel="stylesheet" href="${bootstrap}"/>
	<!-- can also just do this..
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/style.css" rel="stylesheet"> 
    -->
	
  </head>
  <body>
      <div class="container-fluid">
      <div class="row">
          <div class="col-md-12">
              <%@include file="/WEB-INF/views/includes/navigation.jsp" %>
              
