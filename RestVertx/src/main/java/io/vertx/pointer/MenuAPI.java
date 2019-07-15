package io.vertx.pointer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.vertx.core.*;
//import io.vertx.core.http.HttpMethod;
//import io.vertx.core.http.HttpServer;
//import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.core.AbstractVerticle;
//import io.vertx.core.MultiMap;
//import io.vertx.ext.web.handler.CorsHandler;

public class MenuAPI extends AbstractVerticle {
	
	// Stores Menu
	private Map<String,List<Menu>> _bucketListMenuItems = new LinkedHashMap();
	private ArrayList<Login> _listLogedUsers = new ArrayList<Login>();
	
	//@Override
	/*public void start(Future<Void> fut) 
	{
		PopulateMenu();
		// Create a router object.
		Router router = Router.router(vertx);
		// Bind "/" to our hello message - so we are still compatible.
		
		router.route("/").handler(routingContext -> 
		{
			CorsHandler.create("https://localhost:4200/")
			.allowedMethod(io.vertx.core.http.HttpMethod.GET)
			.allowedMethod(io.vertx.core.http.HttpMethod.POST)
			.allowedMethod(io.vertx.core.http.HttpMethod.OPTIONS)
			.allowCredentials(true)
			.allowedHeader("Access-Control-Allow-Method")
			.allowedHeader("Access-Control-Allow-Origin")
			.allowedHeader("Access-Control-Allow-Credentials")
			.allowedHeader("Content-Type");
		
			//HttpServerResponse response = routingContext.response();
			//response.putHeader("content-type", "text/html").end("<h1>Hello from my first Vert.x 3 application</h1>");
		});
		
		// Serve static resources from the /assets directory
		router.route("/assets/*").handler(StaticHandler.create("assets"));
		router.get("/api/menu").handler(this::getAll);
		
		router.route("/api/login*").handler(BodyHandler.create());
		router.post("/api/login").handler(this::Login);
		router.get("/api/login").handler(this::getUsers);
		
		router.post("/api/logout").handler(this::Logout);
		
		// Create the HTTP server and pass the "accept" method to the request handler.
		vertx.createHttpServer()
	     .requestHandler(router::accept)
	     .listen(
	         // Retrieve the port from the configuration,
	         // default to 8080.
	         config().getInteger("http.port", 8080),
	         result -> 
	         {
	           if (result.succeeded()) 
	           {
	        	   fut.complete();
	           } 
	           else 
	           {
	        	   fut.fail(result.cause());
	           }
	         }
	     );
	}*/
	
	@Override
	public void start(Future<Void> fut) {
	 Router router = Router.router(vertx);
	 router.route("/").handler(routingContext -> {
	   HttpServerResponse response = routingContext.response();
	   response
	       .putHeader("content-type", "text/html")
	       .end("<h1>Hello from my first Vert.x 3 application</h1>");
	 });

	 PopulateMenu();
	 // Serve static resources from the /assets directory
	 router.route("/assets/*").handler(StaticHandler.create("assets"));
	 
	 router.get("/api/menu").handler(this::getAll);
	 router.route("/api/login*").handler(BodyHandler.create());
	 router.post("/api/login").handler(this::Login);
	 router.get("/api/login").handler(this::getUsers);
	 router.post("/api/logout").handler(this::Logout);
	 
	 vertx
	     .createHttpServer()
	     .requestHandler(router::accept)
	     .listen(
	         // Retrieve the port from the configuration,
	         // default to 8080.
	         config().getInteger("http.port", 8080),
	         result -> {
	           if (result.succeeded()) {
	             fut.complete();
	           } else {
	             fut.fail(result.cause());
	           }
	         }
	     );
	}

	private void getAll(RoutingContext routingContext) 
	{
		  routingContext.response()
		      .putHeader("content-type", "application/json; charset=utf-8")
		      .end(Json.encodePrettily(_bucketListMenuItems));
	}
	
	private void getUsers(RoutingContext routingContext) 
	{
		  routingContext.response()
		      .putHeader("content-type", "application/json; charset=utf-8")
		      .end(Json.encodePrettily(_listLogedUsers.toArray()));
	}
	// Create some product
	private void PopulateMenu() 
	{
		String sBeef = "Beef";
		Menu item1 = new Menu(sBeef, "Roast beef");
		Menu item2 = new Menu(sBeef, "Shawarma");
		Menu item3 = new Menu(sBeef, "Hamburger");
		Menu item4 = new Menu(sBeef, "Meatballs");
		//add beef dish to bucket
		List<Menu> listTypeItems = new ArrayList<Menu>()
			{{
				add(item1);
				add(item2);
				add(item3);
				add(item4);
			}};
		_bucketListMenuItems.put(sBeef, listTypeItems);

		String sChicken = "Chicken";
		Menu item5 = new Menu(sChicken, "Schnitzel");
		Menu item6 = new Menu(sChicken, "Chicken Breast");
		Menu item7 = new Menu(sChicken, "stir-fry");

		//add beef dish to bucket
		listTypeItems = new ArrayList<Menu>()
			{{
				add(item5);
				add(item6);
				add(item7);
			}};
		_bucketListMenuItems.put(sChicken, listTypeItems);
		
		String sFish = "Fish";
		Menu item8 = new Menu(sFish, "Fish balls");
		Menu item9 = new Menu(sFish, "Fried fish");
		Menu item10 = new Menu(sFish, "Amnon fish");

		//add beef dish to bucket
		listTypeItems = new ArrayList<Menu>()
			{{
				add(item8);
				add(item9);
				add(item10);
			}};
		_bucketListMenuItems.put(sFish, listTypeItems);
		
		String sVegan = "Vegan";
		Menu item11 = new Menu(sVegan, "Mushroom Pie");
		Menu item12 = new Menu(sVegan, "Vegetable lazania");

		//add beef dish to bucket
		listTypeItems = new ArrayList<Menu>()
			{{
				add(item11);
				add(item12);
			}};
		_bucketListMenuItems.put(sVegan, listTypeItems);
		
		String sSideDish = "Side dish";
		Menu item13 = new Menu(sSideDish, "Rice");
		Menu item14 = new Menu(sSideDish, "Potato chips");
		Menu item15 = new Menu(sSideDish, "Puree");
		//add beef dish to bucket
		listTypeItems = new ArrayList<Menu>()
			{{
				add(item13);
				add(item14);
				add(item15);
			}};
		_bucketListMenuItems.put(sSideDish, listTypeItems);          	 
	}
	
	private void Login(RoutingContext routingContext) 
	{
		  final Login loginData = Json.decodeValue(routingContext.getBodyAsString(), Login.class);
		  
		  _listLogedUsers.add(loginData);
		  //Menu item1 = new Menu(loginData.get_sUserName(), loginData.get_sUserPassword());
		  //List<Menu> listTypeItems = new ArrayList<Menu>(){{add(item1);}};
		  //_bucketListMenuItems.put("blblblbll", listTypeItems);
		  
		  //routingContext.response()
		    //  .setStatusCode(201)
		      //.putHeader("content-type", "application/json; charset=utf-8")
		      //.end(Json.encodePrettily(loginData.get_sUserName()));
	}
	
	public void Logout(RoutingContext routingContext)
	{
		  final Login logoutData = Json.decodeValue(routingContext.getBodyAsString(), Login.class);
		  
		  _listLogedUsers.removeIf(x -> x.get_sUserName() == logoutData.get_sUserName());
		  if(_listLogedUsers.remove(logoutData) == false)
		  {
			routingContext.response().setStatusCode(201)
			      .putHeader("content-type", "application/json; charset=utf-8")
			      .end(Json.encodePrettily("Logout failed!"));
		  }
	}
	
	public JsonObject GetMenu()
	{
		return new JsonObject();
	}
	
	public void OrderMeal(JsonObject message)
	{
		
	}
	
	public JsonObject GetTodayOrders()
	{
		return new JsonObject();
	}
}
