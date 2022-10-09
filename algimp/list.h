#ifndef list_h

#define list_h

typedef struct list_t list_t;

struct list_t {
	list_t*		succ;
	list_t*		pred;
	char*		string;
};

list_t* new_list(char* string);
void free_list(list_t** head);
void insert_first(list_t** head, list_t* p);
void insert_last(list_t** head, list_t* p);
void reverse(list_t** head);
void print(list_t* head);

#endif
