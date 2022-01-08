// events.js
var faker = require('faker/locale/it');

function generateEvents () {
  var events = [];
    //var number_loc=faker.datatype.number(7914);
    let url = "https://comuni-ita.herokuapp.com/api/comuni";

	let settings = { method: "Get" };
	const fetch = (...args) => import('node-fetch').then(({default: fetch}) => fetch(...args));
	fetch(url, settings)
    .then(res => res.json())
    .then((json) => {
        var result = [];

        for(var i in json)
            result.push(json[i]);
  		for (var id = 5; id < 100; id++) {
    		var title = faker.name.title();
    		var description = faker.lorem.sentence();
	

            var paeseIndex = faker.datatype.number({
                'min': 0,
                'max': result.length-1
            });

            //console.log(json[paeseIndex]);
            var location=json[paeseIndex];
            //console.log(location.nome);
   

    var type1=faker.random.arrayElement(["1.1.1","1.1.2","1.1.3","1.2.1","1.2.2","1.2.3","1.2.4","1.3.1","1.3.3","1.4.1","1.4.2","1.4.3","1.4.4",
    									"2.1","2.2","2.3","2.4","2.5","2.6","2.7","3.1","3.2","3.3","3.4","3.5"]);
	var type2=faker.random.arrayElement(["1.1.1","1.1.2","1.1.3","1.2.1","1.2.2","1.2.3","1.2.4","1.3.1","1.3.3","1.4.1","1.4.2","1.4.3","1.4.4",
    									"2.1","2.2","2.3","2.4","2.5","2.6","2.7","3.1","3.2","3.3","3.4","3.5"]);
    var datetime=faker.date.future(2);
    
    var dataAAAA=datetime.getFullYear();
    var dataMM=datetime.getMonth()+1;
    var dataGG=datetime.getDate();
    var ora=datetime.getHours().toString().length<2?"0"+datetime.getHours().toString():datetime.getHours().toString();
    var minuti=datetime.getMinutes().toString().length<2?"0"+datetime.getMinutes().toString():datetime.getMinutes().toString();
    var timeEv=ora+":"+minuti;
    var managerId=faker.datatype.number({min:1, max:4});
    var urlImage=faker.image.imageUrl();
    var totSeat=faker.datatype.number({min:50, max:100});
    events.push({
      "id": id,
      "title": title,
      "description": description,
      "location": location.nome+"-"+location.provincia+"-"+location.regione,
      "types": 
      [
      	{"type":type1},
      	{"type":type2}
      ],
      "dataAAAA": dataAAAA,
      "dataMM": dataMM,
      "dataGG": dataGG,
      "time": timeEv,
      "managerId": managerId,
      "urlImage": urlImage,
      "freeSeat": totSeat,
      "totSeat": totSeat
    })
  } 
  
  });
  return { "events": events }
}
module.exports = generateEvents
