var backdrop = document.querySelector(".backdrop");
var modal = document.querySelector(".modal");
var modalNoButton = document.querySelector(".modal__action--no");
var toggleButton = document.querySelector(".toggle-button");
var mobileNav = document.querySelector(".mobile-nav");
var logoutButton = document.querySelector("#logout-button");
var logoutButton2 = document.querySelector("#logout-button2");


if(logoutButton) {
	
	logoutButton.addEventListener("click", function() {
	  // mobileNav.style.display = 'none';
	   console.log("logout button clicked");
	 modal.classList.add("open");
   	 backdrop.classList.add("open");
	});
	
}

if(logoutButton2){

	logoutButton2.addEventListener("click", function() {
	  // mobileNav.style.display = 'none';
	   console.log("logout button 2 clicked");
	    modal.classList.add("open");
   	 	backdrop.classList.add("open");
	});

}

if(backdrop){

	backdrop.addEventListener("click", function() {
	  // mobileNav.style.display = 'none';
	  if(mobileNav){
	  qmobileNav.classList.remove("open");
	  }
	  closeModal();
	});

}

if (modalNoButton) {
  modalNoButton.addEventListener("click", closeModal);
}

function closeModal() {
  //   backdrop.style.display = "none";
  //   modal.style.display = "none";
  if (modal) {
    modal.classList.remove("open");
  }
  if(backdrop){
  	backdrop.classList.remove("open");
  }
}

if(toggleButton){

	toggleButton.addEventListener("click", function() {
	  // mobileNav.style.display = 'block';
	  // backdrop.style.display = 'block';
	  if(mobileNav){
	  	mobileNav.classList.add("open");
	  }
	  if(backdrop){
	  	backdrop.classList.add("open");
	  }
	});
}