#include "localpasswd.h"

#include <stdio.h>
#include <string.h>

static const char FILENAME[] = "localpasswd";

struct passwd *localgetpwnam(const char *name)
{
	struct passwd *p = NULL;
	FILE *localpasswd;

	/* Open our local password file and hope everything works. */
	localpasswd = fopen(FILENAME, "r");
	if (!localpasswd) {
		return NULL;
	}

	/* Use the system functions that walk through local passwd files. */
	while ((p = fgetpwent(localpasswd))) {
		if (strcmp(p->pw_name, name) == 0) {
			return p; /* Found ya. */
		}
	}

	return NULL; /* Nothing found :( */
}

