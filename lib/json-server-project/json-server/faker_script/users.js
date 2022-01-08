// users.js
var faker = require('faker/locale/it');

function generateUsers () {
  var users = [];
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
  		for (var id = 4; id < 5000; id++) {
    		var name = faker.name.firstName();
    		var surname = faker.name.lastName();
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
    var type3=faker.random.arrayElement(["1.1.1","1.1.2","1.1.3","1.2.1","1.2.2","1.2.3","1.2.4","1.3.1","1.3.3","1.4.1","1.4.2","1.4.3","1.4.4",
    									"2.1","2.2","2.3","2.4","2.5","2.6","2.7","3.1","3.2","3.3","3.4","3.5"]);
    var datetime=faker.date.past(80,'2004-01-01');
    
    var dataAAAA=datetime.getFullYear();
    var dataMM=datetime.getMonth()+1;
    var dataGG=datetime.getDate();
    var sex=faker.random.arrayElement(["M","F"]);
    
    users.push({
      "id": id,
      "name": name,
      "surname": surname,
      "sex":sex,
      "dateOfBirth": dataGG+"/"+dataMM+"/"+dataAAAA,
      "residence": location.nome+"-"+location.provincia+"-"+location.regione,
      "types": [
        {
          "type": type1
        },
        {
          "type": type2
        },
        {
          "type": type3
        }
      ],
      "mail": faker.internet.email(),
      "password": faker.internet.password()

    })
  } 
  
  });
  return { "users": users }
}
module.exports = generateUsers