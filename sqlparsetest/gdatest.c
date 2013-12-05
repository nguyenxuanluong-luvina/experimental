#include <stdio.h>
#include <sql-parser/gda-sql-parser.h>

/**
 * main
 */
int main(int argc, char ** argv) {
  int abc = NULL;
  int inbufsize = 4096;
  char inbuf[inbufsize];
  int readsize = 0;
  GdaSqlParser * pParser = NULL;
  GdaStatement * pStatement = NULL;
  GError *pError = NULL;
  GdaSqlStatement *pSqlStatement = NULL;
  /* read stdin as many characters as I can hold. */
  readsize = fread(inbuf, 1, inbufsize - 1, stdin);
  inbuf[ readsize ] = '\0';
  /* Do parse SQL */
  pParser = gda_sql_parser_new();
  pStatement = gda_sql_parser_parse_string(pParser, inbuf, NULL, NULL);
  //Serialize to json
  printf("%s",gda_statement_serialize(pStatement));
 
  return 0;
}

