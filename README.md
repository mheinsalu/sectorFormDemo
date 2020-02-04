Demo assignment:

Tasks:
1. Correct all of the deficiencies in original_index.html

2. "Sectors" selectbox:
2.1. Add all the entries from the "Sectors" selectbox to database
2.2. Compose the "Sectors" selectbox using data from database

3. Perform the following activities after the "Save" button has been pressed: 
3.1. Validate all input data (all fields are mandatory)
3.2. Store all input data to database (Name, Sectors, Agree to terms)
3.3. Refill the form using stored data 
3.4. Allow the user to edit his/her own data during the session


Deployment:

Compile and package with Maven. Deploy resulting .war package into Tomcat's webapps directory. Run Tomcat. 

On external TC server the home page address is 
http://localhost:port/artifact_name/contextPath
. Port and contextPath can be changed in application.properties. 
Artifact_name is defined in pom.xml's finalName tag.

This application uses an H2 in-memory database to store data. H2 console's address is 
http://localhost:port/artifact_name/contextPath/h2-console
 . Its User Name, Password and relative path are defined in application.properties. 