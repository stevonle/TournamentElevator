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

export function getGameIcon(gameId) {
  switch (gameId) {
    case 1:
      return "/img/Icons/football.png";
    case 2:
      return "/img/Icons/soccer.png";
    case 3:
      return "/img/Icons/basketball.png";
    case 4:
      return "/img/Icons/volleyball.png";
    default:
      return "/img/Icons/default.png";
  }
}

export function isHost(tournament) {
  if (!tournament) return false;
  return this.$store.state.user.id === tournament.host;
}
