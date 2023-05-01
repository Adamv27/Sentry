<p align="center"><img width=45% src="https://github.com/Adamv27/Sentry/blob/master/media/Sentry.png"></p>
<div align="center">
  <strong>Password Manager</strong>
 </div>
 <div align="center">
  Personal Project Spring 2023
 </div>
 
 <!-- TABLE OF CONTENTS -->
<h2 id="table-of-contents"> :book: Table of Contents</h2>
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="showcase"> ➤ Showcase</a></li>
    <li><a href="#about-the-project"> ➤ About The Project</a></li>
    <li><a href="#features"> ➤ Features</a></li>
    <li><a href="requirements"> ➤ Requirements</a></li>
    <li><a href="#project-structure"> ➤ Project Structure</a></li>
    <li><a href="#disclaimers"> ➤ Disclaimers</a></li>
  </ol>
</details>


<!-- Showcase -->
<h2 id="showcase">Showcase</h2>

<p float="left">
  <img src="https://github.com/Adamv27/Sentry/blob/master/media/Login.png" width="400" />
  <img src="https://github.com/Adamv27/Sentry/blob/master/media/Main.png" width="400" /> 
</p>
  



<!-- ABOUT THE PROJECT -->
<h2 id="about-the-project"> :pencil: About The Project</h2>

<p align="justify"> 
  Sentry is a desktop password manager built with Swing. User information is stored locally in an SQLite database. This project   was designed using the <strong>MVC</strong> architecture.
</p>

 
<!-- Features -->
<h2 id="features"> 💻 Features</h2>

<ul>
  <li>Account creation</li>
  <li>Searching through saved passwords</li>
  <li>Adding new passwords</li>
  <li>Editting passwords</li>
  <li>Deleting passwords</li>
</ul>

<!-- Requirements -->
<h2 id="requirements"> Requirements</h2>
 <li>Java 8+ Platform (<a href="http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html">JRE</a> for Linux and Windows or <a href="http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html">JDK</a> for macOS)

<!-- Project Structure -->
<h2 id="project-structure"> 📂: Project Structure</h2>

```
sentry/
├── model/            # Backend of application and related data classes
├── view/             # Front end of application
│   ├── frames/
│   ├── panels/
│   └── components/
├── controller/       # Controller to interact with view
├── Sentry.java
└── ...
```


<!-- Disclaimers -->
<h2 id="disclaimers"> ⚠️: Disclaimers</h2>

<p align="justify"> 
  Sentry was built so I could practice software development and was <strong>NOT</strong> built to be a secure way of storing passwords. All user website data is encrypted with a base 64 encoding scheme and placed into the database. While this is somewhat more secure than just storing plain text, anyone can decrypt this data by looking at the source code so please do not enter <strong>ANY</strong> sensative information in Sentry or it risks being compromised. 
</p>
