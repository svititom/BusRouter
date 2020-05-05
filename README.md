# BusRouter
Application to visualize bus routes in singapore

## Running
You will need an LTA DataMall Account key, signup here: [Lta Datamall](https://www.mytransport.sg/content/mytransport/home/dataMall/request-for-api.html)
Set your account as an environment variable - LTA_DATAMALL_ACCOUNT_KEY

Currently the application downloads all bus stops and bus routes, and persists them to storage

## Next steps:
1. Create view to select bus route and display all the bus stops in set direction
2. Add map frontend, probably D3 (We could draw the maps with just google maps or tomtom, but if we want to join the bus stops with routes, that would become expensive fast)
