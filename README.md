# BusRouter
Application to visualize bus routes in singapore

## Running
You will need an LTA DataMall Account key, signup here: [Lta Datamall](https://www.mytransport.sg/content/mytransport/home/dataMall/request-for-api.html)
Set your account as an environment variable - LTA_DATAMALL_ACCOUNT_KEY

Currently we can only download the list of bus stops and persist them


## Next steps:
1. Download list of bus routes and map them to the bus stops
2. Create view to select bus route and display all the bus stops in set direction
3. Add map frontend, probably D3 (We could draw the maps with just google maps or tomtom, but if we want to join the bus stops with routes, that would become expensive fast)
