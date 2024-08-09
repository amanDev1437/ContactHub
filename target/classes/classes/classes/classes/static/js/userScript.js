
function deleteContact(contactId){

                 swal({
                         title: "Are you sure?",
                         icon: "warning",
                         buttons: true,
                         dangerMode: true,
                 })
                 .then((willDelete) => {
                                     if (willDelete) {
                                     window.location = "/user/delete/"+contactId
                                      }
                 });
}

function search(){
    let query = $("#search-input").val();

    console.log(query);

    console.log("searching");

    if(query==''){
        $(".search-result").hide();
    }else{
        
        let url = "http://localhost:8080/search/"+query;

        console.log(url);

        fetch(url).then((res)=>{
            return res.json()
        }).then((data)=>{
            let text = `<div class='list-group'>`

            data.forEach((contact) => {

                text += `<a href=# class='list-group-item list-group-item-action search-list'>${contact.name}</a>`;
            
            });
            text += `</div>`;

            $(".search-result").html(text)
            $(".search-result").show();
        });

        
    }
}