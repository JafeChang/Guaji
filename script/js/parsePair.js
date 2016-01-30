var Pair = function(type,title,key,val,growth,desc){
	this.type = type;
	this.title = title;
	this.key = key;
	this.val = val;
	this.growth = growth;
	this.desc = desc;
}

var pairs = [];

var parsePair = function(jsonPair){
	title = jsonPair.title;
	key = jsonPair.key;
	val = jsonPair.value;
	growth = jsonPair.growth;
	desc = jsonPair.desc;
	type = jsonPair.type;
	var p = new Pair(type,title,key,val,growth,desc);
	//console.log(p)
	return p;
}

var parsePairs = function(text){
		return JSON.parse(text);
}

var jsonToNode = function(jsonPair,ele){
	children = ele.children;
	for(i=0;i<children.length;i++){
		child = children[i];
		if(child.getAttribute('ele')==='type'){
			child.value = jsonPair.type;
		}else if(child.getAttribute('ele')==='title'){
			child.value = jsonPair.title;
		}else if(child.getAttribute('ele')==='key'){
			child.value = jsonPair.key;
		}else if(child.getAttribute('ele')==='value'){
			child.value = jsonPair.value;
		}else if(child.getAttribute('ele')==='growth'){
			child.value = jsonPair.growth;
		}else if(child.getAttribute('ele')==='desc'){
			child.value = jsonPair.desc;
		}else if(child.getAttribute('ele')==='price'){
			if(!jsonPair.price){
				jsonPair.price = "";
			}
			child.value = jsonPair.price;
		}else if(child.getAttribute('ele')==='productions'){
			if(!jsonPair.productions){
				jsonPair.productions="";
			}
			child.value = jsonPair.productions;
		}
	}
	// //console.log("jsonToNode:[jsonPair="+jsonPair+"][ele="+ele+"]");
}

var nodeToJson = function(ele){
	jsonPair = {};
	children = ele.children;
	for(i=0;i<children.length;i++){
		child = children[i];
		if(child.getAttribute('ele')==='type'){
			jsonPair.type = child.value;
		}else if(child.getAttribute('ele')==='title'){
			jsonPair.title = child.value;
		}else if(child.getAttribute('ele')==='key'){
			jsonPair.key = child.value;
		}else if(child.getAttribute('ele')==='value'){
			jsonPair.value = child.value;
		}else if(child.getAttribute('ele')==='growth'){
			jsonPair.growth = child.value;
		}else if(child.getAttribute('ele')==='desc'){
			jsonPair.desc = child.value;
		}else if(child.getAttribute('ele')==='price'){
			if(child.value==""||!child){
				jsonPair.price='';
			}else{
				jsonPair.price = child.value;
			}
			// jsonPair.price = child.value;
		}else if(child.getAttribute('ele')==='productions'){
			if(child.value==""||!child){
				jsonPair.productions='';
			}else{
				jsonPair.productions = child.value;
			}
			// jsonPair.productions = child.value;
		}
	}
	return jsonPair;
}

var jsonarrayToString = function(ja){
	// //console.log(ja)
	var str = "[\n";
	if(ja.length>0){
		str += "    "+ja[0];
	}
	for(var i=1;i<ja.length;i++){
		str += ",\n    "+ja[i];
	}
	str+="\n]";
	return str;
}

var addJsonToArray = function(array,jsonPair){
	array.push(JSON.stringify(jsonPair));
}

var isEmptyNode = function(ele){ 
	children = ele.children;
	for(var i=0;i<children.length;i++){
		if(!isGood(children[i].value)&&children[i].type!='checkbox'&&children[i].getAttribute('ele')!='price'&&children[i].getAttribute('ele')!='productions'){
			return true
		}
	}
	return false;
}

var isEmptyNode2 = function(ele){ 
	children = ele.children;
	for(var i=0;i<children.length;i++){
		if(!isGood(children[i].value)&&children[i].getAttribute('ele')!='title'&&children[i].getAttribute('ele')!='minus'){
			return true
		}
	}
	return false;
}

var isGood = function(text){
	return text?text.length>0:false;
}

var addPrice = function(jsonPrice, type, key, value){
	
}
var selectBtn = null;
var priceOrProductions = 0;

var getPrice = function(bt){
	if(selectBtn){
		setBtnColor(selectBtn,false);
	}
	selectBtn = bt;
	setBtnColor(bt,true);
	priceOrProductions = 1;
	clearInputBar2();
	current2 = 0;
	var ele = bt.parentNode;
	//console.log(ele);
	var json = nodeToJson(ele);
	var prices = json.price.split(';');
	jsonPairs = parsePairs(f("jsonsrc").value);
	for(var i=0;i<jsonPairs.length;i++){
		var jsonPair = jsonPairs[i];
		if(jsonPair.type===json.type&&json.key===jsonPair.key){
			// //console.log(jsonPair);
			// return jsonPair.price;
			// var prices = jsonPair.price.split(';');
			for(var j=0;j<prices.length;j++){
				addInputBar2(prices[j]);
			}
			// for(var j=0;j<ja.length;j++){
			// 	addInputBar2(ja[j]);
			// }
			// addInputBar2(jsonPair.price);
		} 
	}
	return null;
}

var getProductions = function(bt){
	if(selectBtn){
		setBtnColor(selectBtn,false);
	}
	selectBtn = bt;
	setBtnColor(bt,true);
	priceOrProductions = 2;
	clearInputBar2();
	current2 = 0;
	var ele = bt.parentNode;
	//console.log(ele);
	var json = nodeToJson(ele);
	var productions = json.productions.split(';');
	jsonPairs = parsePairs(f("jsonsrc").value);
	for(var i=0;i<jsonPairs.length;i++){
		var jsonPair = jsonPairs[i];
		if(jsonPair.type===json.type&&json.key===jsonPair.key){
			//console.log(jsonPair);

			// var productions = jsonPair.productions.split(';');
			for(var j=0;j<productions.length;j++){
				addInputBar2(productions[j]);
			}
			// return jsonPair.productions;
		} 
	}
	return null;
}

var getPair = function(type, key){
	for(var i=0;i<pairs.length;i++){
		if(pairs[i].type == type&&pairs[i].key==key){
			return pairs[i];
		}
	}
	return null;
}

var priceToNode = function(price,ele){ 
	var type = price[0];
	var key = price[1];
	var value = price[2];
	//console.log("price="+price+"with ("+type+","+key+")");
	var title = getPair(type,key).title;

	var children = ele.children;
	for(var i=0;i<children.length;i++){
		if(children[i].getAttribute('ele')==='type'){
			children[i].value = type;
		}else if(children[i].getAttribute('ele')==='key'){
			children[i].value = key;
		}else if(children[i].getAttribute('ele')==='value'){
			children[i].value = value;
		} else if(children[i].getAttribute('ele')==='title'){
			children[i].value = title;
		}
	}
}

var clearInputBar = function(){
	var children = f('devide').children;
	var toDelArray = [];
	for(var i=0;i<children.length;i++){
		if(children[i].className!='not-clear'){
			toDelArray.push(children[i]);
		}
	}
	for(var i=0;i<toDelArray.length;i++){
		toDelArray[i].parentNode.removeChild(toDelArray[i]);
	}
}

var clearInputBar2 = function(){
	var children = f('devide2').children;
	var toDelArray = [];
	for(var i=0;i<children.length;i++){
		if(children[i].id!='titlebar2'&&children[i].id!='hiddenbar2'){
			toDelArray.push(children[i]);
		}
	}
	for(var i=0;i<toDelArray.length;i++){
		toDelArray[i].parentNode.removeChild(toDelArray[i]);
	}
}

var setBtnColor = function(btn,flag){
	if(flag){
		btn.style.background='#3385ff';
	}else{
		btn.style.background ='#fff';
	}
}

var setPriceOrProductions = function(){
	var children = f('devide2').children;
	var pstr = "";
	for(var i=0;i<children.length;i++){
		if(children[i].getAttribute('totraverse')&&!isEmptyNode2(children[i])){
			pstr +=  nodeToPrice(children[i]);
		}
	}
	if(pstr.charAt(0)===';'){
		pstr = pstr.substring(1,pstr.length);
	}
	getPriceOrProductionsInput().value = pstr;
	setBtnColor(selectBtn,false);
	selectBtn = null;
	priceOrProductions = 0;

}

var nodeToPrice = function(ele){ 
	var type = "";
	var key = "";
	var value = "";
	children = ele.children;
	for(i=0;i<children.length;i++){
		child = children[i];
		if(child.getAttribute('ele')==='type'){
			type = child.value;
		}else if(child.getAttribute('ele')==='key'){
			key = child.value;
		}else if(child.getAttribute('ele')==='value'){
			value = child.value;
		}
	}
	if(isEmptyNode2(ele)){
		return "";
	}
	return ";"+type+","+key+","+value;
}

var getPriceOrProductionsInput = function(){
	var parent = selectBtn.parentNode;
	var children = parent.children;
	for(var i=0;i<parent.children.length;i++){
		if(priceOrProductions===1){
			if(children[i].getAttribute('ele')==='price'){
				return children[i];
			}
		}else if(priceOrProductions===2){
			if(children[i].getAttribute('ele')==='productions'){
				return children[i];
			}
		}
	}
	return null;
}

var addNewInputBar2 = function(){
	dest = f("pairsrc2").cloneNode(true);
	dest.setAttribute("id","input2Div"+current2);
	current2++;
	f("devide2").appendChild(dest);
}

var addInputBar2 = function(price){
	dest = f("pairsrc2").cloneNode(true);
	dest.setAttribute("id","input2Div"+current2);
	current2++;
	if(price||price!=''){
		priceToNode(price.split(','),dest);
	}
	f("devide2").appendChild(dest);

}

 var removeBar = function(btn){
 	btn.parentNode.parentNode.removeChild(btn.parentNode);
 }

