Basically the first top part has the displayInitialTable() method which will basically be the method in which the initial table will be created.
Within this displayInitialTable() method, u can call the getUserInfo() method in the controller, and then its the same thing as before to the entity back to the controller and boundary.
After that, pass the data to the viewTableConstruction() method, which can exist within the displayInitialTable() method, for processing and displaying.

Same goes for the other two functions, the viewTableConstruction() method can exist within the Onclick button function, as long as the function included
does not have any complex if/else statements that will call another function.

As for the refresh function, i rmb it being mentioned that it still works find within the construction() method. so please keep it within the construction method.

As mentioned last night, please dont do like 
if(statement1)
{
     println("");
}
else
{
 Calls Method1
}


public void Method1()
if(statement2)
{
	println("");
}
else
{
	Calls Method2
}

That would be pretty fucked. You can refer to the picture i sent you with the doodles to further understand stuff you dont understand. Thanks
