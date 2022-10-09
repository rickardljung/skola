/* 
 * This file may be used instead of localpasswd.c when compiling on a Mac OS X system.
 * It has been tested on OS X Mavericks only, but may work also in Linux.
 *
 * The same header (localpasswd.h) can be used, just replace localpasswd.c with this
 * file when compiling, e.g: gcc -o mylogin mylogin.c localpasswdmac.c
 * NOTE: When compiling on OS X, -lcrypt MUST NOT be included.
 */

#include "localpasswd.h"

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static const char FILENAME[] = "localpasswd";

/* We ignore potential memory allocation failures on malloc. */

void free_and_null(char **mem)
{
	free(*mem);
	*mem = NULL;
}

bool store_next_field(char **field, char **tok)
{
	*tok = strtok(NULL, ":");
	if (!tok) {
		return false; /* Invalid format. */
	}
	*field = malloc(strlen(*tok) + 1);
	strcpy(*field, *tok);
	return true;
}

/* Yeah, POSIX does not say anything about the type of uid_t or gid_t. Let's assume unsigned. */
bool store_next_field_int(unsigned *field, char **tok)
{
	*tok = strtok(NULL, ":");
	if (!tok) {
		return false; /* Invalid format. */
	}
	*field = atoi(*tok); /* Also assume this is actually an int. */
	return true;
}

struct passwd *localgetpwnam(const char *name)
{
	/* Since OS X does not include fgetpwent by default, we cannot use it on OS X. */
	static struct passwd p;
	struct passwd *pp = &p;
	FILE *localpasswd;

	/* Open our local password file and hope everything works. */
	localpasswd = fopen(FILENAME, "r");
	if (!localpasswd) {
		return NULL;
	}

	/* Read every line. */
	while (1) {
		char *line = NULL;
		size_t linesize = 0; /* Size of buffer */
		ssize_t linelen = 0; /* Length of content within buffer. */
		if ((linelen = getline(&line, &linesize, localpasswd)) < 0) goto nouser; /* No user. */

		/* Now, try to find the username in this line, by splitting at the first colon. */
		char *str = line;
		char *tok = NULL;
		tok = strtok(str, ":");
		if (!tok) goto invalid_format;
		if (strcmp(tok, name) == 0) {
			/* User found! Now let's read all the other stuff.
			 * But first, reset the previous static passwd struct. */
			free_and_null(&p.pw_name);
			free_and_null(&p.pw_passwd);
			free_and_null(&p.pw_gecos);
			free_and_null(&p.pw_dir);
			free_and_null(&p.pw_shell);

			p.pw_name = malloc(strlen(tok) + 1);
			strcpy(p.pw_name, tok);

			if (!store_next_field(&p.pw_passwd, &tok)) goto invalid_format;
			if (!store_next_field_int(&p.pw_uid, &tok)) goto invalid_format;
			if (!store_next_field_int(&p.pw_gid, &tok)) goto invalid_format;
			if (!store_next_field(&p.pw_gecos, &tok)) goto invalid_format;
			if (!store_next_field(&p.pw_dir, &tok)) goto invalid_format;
			if (!store_next_field(&p.pw_shell, &tok)) goto invalid_format;
			/* Everything worked! Now we should just return the entry. */
			free(line);
			goto cleanup;
		}
invalid_format:
		free(line);
	}

nouser:
	pp = NULL;
cleanup:
	fclose(localpasswd);
	return pp;
}

