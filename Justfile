# Shows available recipies
help:
      just --list

run:
      clojure -M -m buzaga.handler

nrepl:
      clojure -M:nREPL -m nrepl.cmdline
