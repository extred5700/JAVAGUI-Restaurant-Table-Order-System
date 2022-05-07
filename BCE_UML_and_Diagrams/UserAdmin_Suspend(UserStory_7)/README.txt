Explanation for UserAdminSuspend BCE UML

1)	It will show displaySuspendPanel which should display the necessary UI. Next, similar to the userAdminView, use an external
	Table creation function to create the table for the users to select. You can either use the viewTableConstruction to create it
	or if you want, you can probably process data and create it inside the new method. Please update me whenever u decide what to do.
	(Not sure if eventlisteners can still remain in displaySuspendPanel or whether they have to be moved to the new external function.)
	
2)	The retrival of data is the same as that in the userAdminView method.

3)	Afterwards, the user selects an account from the table, and then the next chunk of diagram.

4)	In this chunk, the suspendPanelButton_Onclick() will pass the data to the controller suspendUser method, and it will go to the entity to update
	the suspension status. Once that is done, it will most likely always return True unless there is some omega fucked shit going on.
	
5)	Lastly, the construct method is called to display and refresh the new table. PLEASE REMEMBER THAT THE REFRESH FUNCTION SHOULD BE IN THAT METHOD.