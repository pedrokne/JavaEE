/**
 * Conformacao de exclusao
 *
 *@author Pedro Henrique Knebel
 *@param idcon 
 */
 
 function confirmar(idcon){
	let resposta = confirm("Confirma a exclusão desde contato?")
	if(resposta === true){
		window.location.href = "delete?idcon=" + idcon
		
	}
	
}