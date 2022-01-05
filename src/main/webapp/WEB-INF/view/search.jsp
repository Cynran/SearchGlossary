<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1"name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="autocomplete.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="main.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Merriweather|Open+Sans">

<title>Keyforge glossary search</title>
</head>

<body>
  <div>
      <h1>keyforge glossary</h1>
  </div>
    <form action="/search" method="get" autocomplete="off">
        <div class="row">
            <div class="col-3"></div>
            <div class="col-6 suggestions" id="sgst_populate">
                <ul>
                    <li>
                      <div class="search">
                        <input type="text" id="keyword" name="keyword" placeholder="Search..." autocomplete="off" oninput="getSuggestions()"> 
                      </div>
                    </li>
                    <li>
                    	<div class="keywordMissing">${error}</div>
                  </li>
                    <li><input type="submit" value="Search"></li>
                </ul>
              </div>
          <div class="col-3"></div>
      </div>
 </form>
 
 <div class="row">
    <div class="col-3"></div>
    <div class="col-6 keyword">${keyword}</div>
    <div class="col-3"></div>
  </div>
  
 <div class="row">
    <div class="col-3"></div>
    <div class="col-6 description"><p>${description}</p></div>
    <div class="col-3"></div>
  </div>
  

    <div class="footer">Disclaimer: Keyforge is the intellectual property of Fantasy Flight Games. 
    This site is not affiliated or sponsored by Fantasy Flight Games. 
    I used the publicly available rules <a href="https://images-cdn.fantasyflightgames.com/filer_public/bb/3d/bb3d8067-b028-43c4-97c8-f76ff55232ba/keyforge_rulebook_v151.pdf">here</a> to create this unoffical glossary search.
    </div>


</body>

</html>