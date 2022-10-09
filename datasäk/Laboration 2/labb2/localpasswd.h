#ifndef LOCALPASSWD_H
#define LOCALPASSWD_H

#include <sys/types.h>
#include <pwd.h>

/* We use the same struct passwd as the system's getpwnam. */

struct passwd *localgetpwnam(const char *name);

#endif /* LOCALPASSWD_H */
