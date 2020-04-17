package fr.hagenbourger.acdt.api.ast

import fr.hagenbourger.acdt.api.ast.element.ChangelogEntry

data class Changelog(val entries: List<ChangelogEntry>)