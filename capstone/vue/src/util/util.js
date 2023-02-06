export function getGameName(type) {
  switch (type) {
    case 1:
      return "Football";
    case 2:
      return "Soccer";
    case 3:
      return "Basketball";
    case 4:
      return "Volleyball";
    case 5:
      return "Quidditch";
    default:
      return "Unknown";
  }
}

export function isHost(tournament) {
  if (!tournament) return false;
  return this.$store.state.user.id === tournament.host;
}
