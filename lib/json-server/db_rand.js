// events.js
var faker = require('faker/locale/it');
var jsonData = require('./province.json');
var json=require('./comuni.json');
var managerA=require('./managers.json');
var userA=require('./users.json');
var eventA=require('./events_future.json');
var managers =[];
var events = []; 
var users = [];
var resultC = [];

for(var i in json)
            resultC.push(json[i]);


function generateEvents () {
  
    //var number_loc=faker.datatype.number(7914);
   /* let url = "https://comuni-ita.herokuapp.com/api/comuni";*/

  		for (var id = 0; id <= 16000; id++) {
			var title ='EVENTO '+id; 		 
    		var description = faker.lorem.paragraph();
	

            var paeseIndex = faker.datatype.number({
                'min': 0,
                'max': resultC.length-1
            });

            //console.log(json[paeseIndex]);
			var via=faker.address.streetName() + ", " +faker.datatype.number({
                'min': 0,
                'max': 100
            });
            var location=json[paeseIndex];
			var provincia=jsonData.find(item => item.codice===location.provincia);
            //console.log(location.nome);
   			var price=faker.datatype.float({'min':0,'max':100,'precision':0.10});

    var type1=faker.random.arrayElement(["1.1.1","1.1.2","1.1.3","1.2.1","1.2.2","1.2.3","1.2.4","1.3.1","1.3.3","1.4.1","1.4.2","1.4.3","1.4.4",
    									"2.1","2.2","2.3","2.4","2.5","2.6","2.7","3.1","3.2","3.3","3.4","3.5"]);
	var type2=type1;
	if(type2==type1){
		type2=faker.random.arrayElement(["1.1.1","1.1.2","1.1.3","1.2.1","1.2.2","1.2.3","1.2.4","1.3.1","1.3.3","1.4.1","1.4.2","1.4.3","1.4.4",
    									"2.1","2.2","2.3","2.4","2.5","2.6","2.7","3.1","3.2","3.3","3.4","3.5"]);
	}
    var datetime=faker.date.past(1).toISOString();
    
    /*var dataAAAA=datetime.getFullYear();
    var dataMM=datetime.getMonth()+1;
    var dataGG=datetime.getDate();
    var ora=datetime.getHours().toString().length<2?"0"+datetime.getHours().toString():datetime.getHours().toString();
    var minuti=datetime.getMinutes().toString().length<2?"0"+datetime.getMinutes().toString():datetime.getMinutes().toString();
    var timeEv=ora+":"+minuti;*/
	var manager;
	/*do{*/
    	manager=faker.random.arrayElement(managerA);
	/*}while(manager.residence.regione!=(provincia.regione.charAt(0).toUpperCase() + provincia.regione.slice(1)))*/
    var urlImage='https://picsum.photos/seed/'+id+'/640/480';
    var totSeat=faker.datatype.number({min:50, max:100});
    var freeSeat=faker.datatype.number({min:0, max:totSeat});
    events.push({
      /*"_id": mongoObjectId() require('mongoose').Types.ObjectId(),*/
      "title": title,
      "description": description,
      "location": {
				"locality" : via,
				"city" : location.nome,
				"cap" : location.cap,
				"provincia" : provincia.nome.charAt(0).toUpperCase() + provincia.nome.slice(1),
				"sigla" : provincia.sigla.toUpperCase(),
				"regione" : provincia.regione.charAt(0).toUpperCase() + provincia.regione.slice(1),
				"lat":location.lat,
				"lng":location.lng
	   },
      "types": 
      [
      	type1,
      	type2
      ],
      "dataOra": datetime,
      "managerId": manager._id,
      "urlImage": urlImage,
	  "price" : price,
      "freeSeat": freeSeat,
      "totSeat": totSeat
    })
  } 
  
  return events
};


function generateUsers () {
 
 
  		for (var id = 1; id <= 5000; id++) {
	 		var uid= faker.datatype.uuid();
		    
    		var name = faker.name.firstName();
    		var surname = faker.name.lastName();
			var username=faker.internet.userName(name,surname);
            var paeseIndex = faker.datatype.number({
                'min': 0,
                'max': resultC.length-1
            });

            //console.log(json[paeseIndex]);
			var via=faker.address.streetName() + ", " +faker.datatype.number({
                'min': 0,
                'max': 100
            });
            var location=json[paeseIndex];
			var provincia=jsonData.find(item => item.codice===location.provincia);
            //console.log(location.nome);
   

    var type1=faker.random.arrayElement(["1.1.1","1.1.2","1.1.3","1.2.1","1.2.2","1.2.3","1.2.4","1.3.1","1.3.3","1.4.1","1.4.2","1.4.3","1.4.4",
    									"2.1","2.2","2.3","2.4","2.5","2.6","2.7","3.1","3.2","3.3","3.4","3.5"]);
	var type2=type1;
	if(type2==type1){
		type2=faker.random.arrayElement(["1.1.1","1.1.2","1.1.3","1.2.1","1.2.2","1.2.3","1.2.4","1.3.1","1.3.3","1.4.1","1.4.2","1.4.3","1.4.4",
    									"2.1","2.2","2.3","2.4","2.5","2.6","2.7","3.1","3.2","3.3","3.4","3.5"]);
	}
	var type1=faker.random.arrayElement(["1.1.1","1.1.2","1.1.3","1.2.1","1.2.2","1.2.3","1.2.4","1.3.1","1.3.3","1.4.1","1.4.2","1.4.3","1.4.4",
    									"2.1","2.2","2.3","2.4","2.5","2.6","2.7","3.1","3.2","3.3","3.4","3.5"]);
	var type3=type1;
	if(type3==type1||type3==type2){
		type3=faker.random.arrayElement(["1.1.1","1.1.2","1.1.3","1.2.1","1.2.2","1.2.3","1.2.4","1.3.1","1.3.3","1.4.1","1.4.2","1.4.3","1.4.4",
    									"2.1","2.2","2.3","2.4","2.5","2.6","2.7","3.1","3.2","3.3","3.4","3.5"]);
	}
    var datetime=faker.date.past(60,'2004-01-01')
		datetime=new Date(datetime.getFullYear(),datetime.getMonth(),datetime.getDate(),1,0,0);
		datetime=datetime.toISOString();
    
    /*var dataAAAA=datetime.getFullYear();
    var dataMM=datetime.getMonth()+1;
    var dataGG=datetime.getDate();*/
    var sex=faker.random.arrayElement(["M","F"]);
    
    users.push({
       /*"_id": mongoObjectId() require('mongoose').Types.ObjectId(),*/
      "username": username,
      "name": name,
      "surname": surname,
      "sex":sex,
      "dateOfBirth": datetime,
      "residence": {
				"locality" : via,
				"city" : location.nome,
				"cap" : location.cap,
				"provincia" : provincia.nome.charAt(0).toUpperCase() + provincia.nome.slice(1),
				"sigla" : provincia.sigla.toUpperCase(),
				"regione" : provincia.regione.charAt(0).toUpperCase() + provincia.regione.slice(1),
				"lat":location.lat,
				"lng":location.lng
	   },
      "types": [
        type1,
        type2,
		type3
      ],
      "mail": faker.internet.email(name,surname),
      "password": faker.internet.password(8,true)

    })
  } 
  
  return users 
};

function generateManagers () {

  		for (var id = 1; id <= 50; id++) {
			var uid= faker.datatype.uuid();
    		var name = faker.name.firstName();
    		var surname = faker.name.lastName();
            var paeseIndex = faker.datatype.number({
                'min': 0,
                'max': resultC.length-1
            });

            //console.log(json[paeseIndex]);
			var via=faker.address.streetName() + ", " +faker.datatype.number({
                'min': 0,
                'max': 100
            });
            var location=json[paeseIndex];
			var provincia=jsonData.find(item => item.codice===location.provincia);
            //console.log(location.nome);
   

    var datetime=faker.date.past(60,'2004-01-01')
		datetime=new Date(datetime.getFullYear(),datetime.getMonth(),datetime.getDate(),1,0,0);
		datetime=datetime.toISOString();

    
    /*var dataAAAA=datetime.getFullYear();
    var dataMM=datetime.getMonth()+1;
    var dataGG=datetime.getDate();*/
    var Piva=faker.finance.routingNumber();
    var RagioneSoc=faker.company.companyName() + " " + faker.company.companySuffix();
    managers.push({
       /*"_id": mongoObjectId() require('mongoose').Types.ObjectId(),*/
      "name": name,
      "surname": surname,
      "dateOfBirth": datetime,
      "residence": {
				"locality" : via,
				"city" : location.nome,
				"cap" : location.cap,
				"provincia" : provincia.nome.charAt(0).toUpperCase() + provincia.nome.slice(1),
				"sigla" : provincia.sigla.toUpperCase(),
				"regione" : provincia.regione.charAt(0).toUpperCase() + provincia.regione.slice(1),
				"lat":location.lat,
				"lng":location.lng
	   },
      "codicePIVA" : Piva,
      "ragioneSociale" : RagioneSoc,
      "mail": faker.internet.email(name,surname),
      "password": faker.internet.password(8,true)

    })
  } 
  

  return managers
};

var requests=[];
function generateRequests () {
  
    //var number_loc=faker.datatype.number(7914);
   /* let url = "https://comuni-ita.herokuapp.com/api/comuni";*/

  		managerA.forEach (function(item, index, array) {
  				var manager;
			/*do{*/
    			manager=item._id;
			//var manager =managerA[i]; 		 
    		var dataIscr = faker.date.past(1);
				dataIscr.setUTCHours(new Number(0));
				dataIscr.setUTCMinutes(new Number(0));
				dataIscr.setUTCSeconds(new Number(0));
				dataIscr.setUTCMilliseconds(new Number(0));
				
				dataIscr=dataIscr.toISOString();
				console.log(dataIscr);
			var requestActive = true;
	
			    requests.push({
			      /*"_id": mongoObjectId() require('mongoose').Types.ObjectId(),*/
			      "managerId": manager,
			      "dateRenewal": dataIscr,
			      "active": requestActive
			    });
			})
			 
  
  return requests
};

var tickets=[];
function generateTickets () {

  		for (var id = 1; id <= 1000; id++) {
			
           
    tickets.push({
       /*"_id": mongoObjectId() require('mongoose').Types.ObjectId(),*/
      "fullName": faker.name.firstName()+" "+faker.name.lastName(),
      "code":"TI-"+faker.datatype.number({
				'min': 1,
                'max': 5200
            })+"-"+faker.finance.mask(),
      "password": faker.internet.password(8,true),
	  "eventId":faker.random.arrayElement(eventA)._id
    })
  } 
  

  return tickets
	};
	
	var bookings=[];
function generateBookings () {
	for(var i=0; i<eventA.length;i++){
		var occSeat=eventA[i].totSeat-eventA[i].freeSeat;
		
  		while (occSeat>0) {
		    
			let pren=0;
			if(occSeat<=4)
				pren=occSeat;
			else
				pren=faker.datatype.number({
					'min': 1,
                	'max': 4
            	});
			occSeat=occSeat-pren;
    		bookings.push({
       			/*"_id": mongoObjectId() require('mongoose').Types.ObjectId(),*/
      			"userId": faker.random.arrayElement(userA)._id,
      			"eventId":eventA[i]._id,
      			"prenotedSeat": pren,
	  			"come":false
    		})
  		} 
	}
  

  		return bookings
};

function generateStatic_also () {
  return {"managers": generateManagers(), "requests": generateRequests(), "events": generateEvents(), "users": generateUsers(),
      
  "admins": [
    {
      /*"_id": mongoObjectId() require('mongoose').Types.ObjectId(),*/
      "mail": "admin@gmail.com",
      "password": "administrator"
    }
  ],
  
  "ticketInspectors": generateTickets()
  ,
  
  "bookings": generateBookings(),
    
  "sponsor": [
    {
      /*"_id": mongoObjectId() require('mongoose').Types.ObjectId(),*/
      "userId": "null",
      "eventId": "null"
    },
    {
      /*"_id": mongoObjectId() require('mongoose').Types.ObjectId(),*/
      "userId": "null",
      "eventId": "null"
    },
    {
      /*"_id": mongoObjectId() require('mongoose').Types.ObjectId(),*/
      "userId": "null",
      "eventId": "null"
      
    }
  ]
} };




module.exports=generateStatic_also;



