# ENI-Encheres aka ENIBay

## Summary

> The association Les objets sont nos amis wishes to set up a web platform to allow the transfer of second hand objects without financial exchange. The value of the items will be determined by an auction system based on a number of points. The points are earned by selling objects, and can then be used to acquire other objects.

## Librairies 📖

### Java 🍵

* [JDK-17.0.5](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [JAXB-API-2.2.8](https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api)
* [Activation-1.1](https://mvnrepository.com/artifact/javax.xml.bind/activation)
* [JAXB-impl-2.2.7](https://mvnrepository.com/artifact/javax.xml.bind/jaxb-impl)
* [JAXB-core-2.2.7](https://mvnrepository.com/artifact/javax.xml.bind/jaxb-core)
* [JSTL-1.2](https://mvnrepository.com/artifact/javax.servlet.jsp.jstl/jstl)
* [Validation-api-1.1.0.Final](https://mvnrepository.com/artifact/javax.validation/validation-api)

### Database 📑

We use MSSQL 2019

* [Sqljdbc42](https://mvnrepository.com/artifact/com.microsoft.sqlserver/sqljdbc42)

### Tomcat 🐈

* [Tomcat 9.0.71](https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.71/)

## Features ✨

| **Iteration** | **Importance** | **Category** | **ID** | **Name** | **Description** |
| --------- | ---------- | -------- | --- | ---- | ----------- |
| 1 | 2000 | User Management | 1001 | Login | As a user, I can log in to the Enchères.org platform with a login (email or username) and a password. |
| 1 | 1900 | User Management | 1003 | Sign up | As a user, I can sign up for the Enchères.org platform. The username must be unique throughout the platform, as well as the email. The username only accepts alphanumeric characters. If the profile creation is validated, the user is directed to the homepage (list of auctions). An initial credit of 100 points is allocated upon account creation. |
| 1 | 1800 | Navigation | 6002 | Homepage | The homepage of the site is the page that lists the auctions. This page should be automatically loaded if no resource is indicated in the url (http://localhost:8080/encheres/) |
| 1 | 1750 | User Management | 1009 | Log out | As a logged-in user, I can log out. I am then returned to the homepage in logged-out mode. |
| 1 | 1700 | User Management | 1006 | View a profile | As a user, I can view the profile of a user. The username, first name, last name, email, phone number, street, zip code, and city are displayed. |
| 1 | 1600 | User Management | 1007 | Edit my profile | As a user, I can edit my profile information: Username, First name, Last name, Email, Phone number, Street, Zip code, City, and password. |
| 1 | 1500 | User Management | 1004 | Delete my account | As a user, I can delete my account. In this case, I am logged out and returned to the homepage. |
| 1 | 1400 | Auction Management | 2001 | Sell an item | As a user, I can sell an item on the ENI-Auctions platform. To do this, I provide information about the item being sold: name, description, and category. I specify a starting price (in points), an auction opening date and time, an auction closing date and time, and withdrawal arrangements (default to seller's address). |
| 1 | 1300 | Auction Management | 2004 | List ongoing auctions in disconnected mode | As a non-connected user, I can list ongoing auctions. I can filter my search by category and item name (the item is displayed if it contains the entered criteria). To view auction details, the user must log in. |
| 1 | 1200 | Auction Management | 2005 | List ongoing auctions in connected mode | As a connected user, I can list ongoing auctions, auctions in which I am participating (i.e. those on which I have made at least one bid), and my won auctions. I can also select my sales, not started, in progress, or completed. |
| 1 | 1100 | Auction Management | 2006 | Make a bid | As a user, I can make a bid on an item if I propose a price (in points) higher than the current rate and if my points balance does not become negative. If the bid is possible, my points credit is debited for the proposed amount. The previous highest bidder, if any, is re-credited with their bid. |
| 1 | 1000 | Auction Management | 2007 | Win a sale | As a bidder, I become the buyer if, at the end of the auction, I have proposed the highest bid. |
| 1 | 950 | Navigation | 6003 | Link to Encheres logo | Clicking on the site logo brings the user back to the main page (item search page) if they are logged in. |
| 1 | 900 | Auction management | 2009 | Display auction details | As a user, I can display the details of an auction. The information about the item being sold is displayed (name, description, highest offer, starting price, start and end of the auction, pick-up address, seller). Depending on the status of the sale and the role of the user (seller or buyer), the user can only view the information, bid, or modify the sale (if they are the seller and the auction has not yet started). |
| 1 | 850 | Navigation | 6001 | Refresh and browser back buttons | As a user, I can refresh the current page or go back to the previous page using the browser's back button. |
| 2 | 800 | Responsive Web Design | 5001 | Mobile version | The features are accessible from a small device such as a web-connected smartphone. See mockups. |
| 2 | 750 | Security | 8001 | User sessions of 5 minutes | The user must be automatically logged out after 5 minutes of inactivity. |
| 2 | 700 | User management | 1002 | Remember me | As a user, I can choose to save my login on my computer so that I do not have to re-enter it at the next login. |
| 2 | 650 | Auction management | 2003 | Cancel a sale | As the seller of an item, I can cancel the sale as long as the auction start date has not been reached. |
| 2 | 600 | Auction management | 2008 | Photo for the sale | As a seller, I can upload a photo of the item for sale. It will be visible on the sale detail page. |
| 2 | 500 | Administration | 3001 | Delete user accounts | As an administrator, I can delete user accounts. |
| 2 | 450 | Administration | 3002 | Deactivate a user account | As an administrator, I can deactivate a user account. All sales offered by this user are then cancelled, all bids made by this user are also cancelled, and the user cannot create new sales or make new bids. |
| 2 | 400 | User management | 1005 | Forgot password | As a user, I can request a password reset. The platform creates a link to a screen for entering the new password. (This link will be sent by email to the user. Email sending is not requested.) |
| 3 | 350 | Auction management | 2010 | Pagination | On the page for listing auctions according to search criteria, I display a maximum number of auctions (6) and access other result pages through numbered links. |
| 3 | 150 | Administration | 3003 | Category management | As an administrator, I can manage, i.e. add, delete, and modify article categories. |
| 3 | 100 | Email notification | 10001 | Notify purchase | On the end date of the auction, a batch process calculates the selling price and notifies the buyer by email. |

## Feature demonstration

### User
___
#### login/logout
<p align="center">
    <img src="/img/connexin-déconnxion.gif" width="70%">
</p>

#### modify profil
<p align="center">
    <img src="/img/modif-compte.gif" width="70%">
</p>

#### rest password
<p align="center">
    <img src="/img/mot-de-passe-oublié.gif" width="70%">
</p>


### Home
___
#### filters
<p align="center">
    <img src="/img/filtres.gif" width="70%">
</p>


### Articles
___
#### Create
<p align="center">
    <img src="/img/creer.gif" width="70%">
</p>

### Auction
<p align="center">
    <img src="/img/enchere.gif" width="70%">
</p>


### Admin
___
<p align="center">
    <img src="/img/dofi.gif" width="70%">
</p>


## Contributors 💜
