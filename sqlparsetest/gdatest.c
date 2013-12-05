#include <stdio.h>
#include <sql-parser/gda-sql-parser.h>


int main(int argc, char ** argv) {
  GdaSqlParser * pParser = NULL;
  GdaStatement * pStatement = NULL;
  pParser = gda_sql_parser_new();
  pStatement = gda_sql_parser_parse_string(pParser, "select * from information_schema.tables where engine = 'InnoDB'", NULL, NULL);
  fprintf(stdout, "%s\n", gda_sql_statement_type_to_string(gda_statement_get_statement_type(pStatement)));
  return 0;
}

