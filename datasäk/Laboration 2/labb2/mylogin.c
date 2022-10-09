/*
 * Program userinfo.c
 * 
 * This program prompts the user for a login name, and tries to 
 * extract user information from the /etc/passwd file.
 *
 */

#define _XOPEN_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <pwd.h>
#include <sys/types.h>
#include <string.h>
#include "pwdblib.h"

#include "localpasswd.h"

/* define some error constants */
#define NOUSER -1

/* define max size of a username */
#define USERNAME_SIZE 32

/* define max size of a userpassword */
#define USERPASSWORD_SIZE 32





int read_username(char *username){

  printf("login: ");
  fgets(username,USERNAME_SIZE,stdin);

 /* remove the CR included by getline() */
  username[strlen(username)-1]='\0';
 
  return(0);
}

char *read_userpassword() {

	return(getpass("password: "));
	
}

int login(const char *username, char *userpassword) {
	struct passwd *pw_entry;
	
	/* change to getpwnam if you would like to use the local /etc/passwd. */
	pw_entry=localgetpwnam(username);
	
	if (pw_entry!=NULL) {
		
	
		if(strcmp(crypt(userpassword, pw_entry->pw_passwd), pw_entry->pw_passwd) == 0) {
		printf("User authenticated successfully! \n");
		return (0);
		}
		else{
		printf("Unknown user or incorrect password. \n");	
		return (1);
		}	
			
	} else {
		printf("No such user! \n");
		return (2);
	}
	

}

int main(int argc,char **argv) {
  char username[USERNAME_SIZE];
  char *userpassword;
  struct pwdb_passwd *p;
  int loop = 0;
	
	do{
  	/* write "login:" and read user input */
	read_username(username);

	/* write "Password:" and read user password */
	userpassword = read_userpassword(userpassword);
	p = pwdb_getpwnam(username);
	
	
		/* Wrong password 5 times*/
		if(p->pw_failed >=4){
		printf("Talk to admin, you are banned! \n");
		loop =0;
		}

		else{
		
		/* Authenticates a user */	
		loop = login(username, userpassword);
	
		/* Unknown user or incorrect password.*/
		if(loop == 1) {
		p->pw_failed = p->pw_failed + 1;
		
		/*User authenticated successfully */	
		}else if(loop ==0 ){
    		p->pw_failed = 0;
		p->pw_age = p->pw_age +1;
		}

		/* Old password */
		if(p->pw_age >=10){
		printf("Change password please! \n");
		p->pw_age = 0;
		
		}
		pwdb_update_user(p);
	
	}


	} while(loop ==1 || loop ==2);

	


  /* Show user info from /etc/passwd */
 // if (print_userinfo(username)==NOUSER) {
      /* if there are no user with that usename... */
     // printf("\nFound no user with name: %s\n",username);   
     // return(0);
  //}

 // return(0);
}
