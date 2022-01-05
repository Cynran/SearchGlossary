function getSuggestions() {	
	const request = ( url, params, method) => {
	    url += '?' + ( new URLSearchParams( params ) ).toString();
	    return fetch(url).then( response => response.json() );
	}

	const get = ( url, params ) => request( url, params, 'GET' );
	const prefix = document.getElementById("keyword").value;
	
	get( '/autocomplete', { keyword: prefix } )
	.then( response => {
		let suggestion = '<ul><li><div class="search"><input type="text" id="keyword" name="keyword" value="' 
							+ prefix 
							+ '" autocomplete="off" oninput="getSuggestions()"/></div></li>';
		
		Object.values(response).forEach(val => 
		suggestion+=
				'<li><button class="suggestion" onclick="change(&apos;'
				+val
				+'&apos;)"><span>'
				+val
				+'</span></button></li>');
		
		suggestion += '<li><input type="submit"  value="Search"></li></ul>';
		document.getElementById("sgst_populate").innerHTML = suggestion;
		
		const input = document.getElementById("keyword");
	    input.focus();
	    input.value = '';
	    input.value = prefix;
	} );

}

function getResult() {
	const myRequest = new Request( url, params, method);
	//const request = ( url, params, method) => {};
	const get = ( url, params ) => myRequest( url, params, 'GET' );
	fetch(myRequest)
	  .catch(error => console.error(error));
	get( '/search', { keyword: document.getElementById("keyword").value } );
}

function change(val){
	document.getElementById("keyword").value = val;
	getResult();	
}