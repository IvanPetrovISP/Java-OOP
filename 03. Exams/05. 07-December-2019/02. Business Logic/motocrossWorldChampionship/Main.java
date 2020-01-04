package motocrossWorldChampionship;
import motocrossWorldChampionship.core.ChampionshipControllerImpl;
import motocrossWorldChampionship.core.EngineImpl;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Engine;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.io.InputReaderImpl;
import motocrossWorldChampionship.io.OutputWriterImpl;
import motocrossWorldChampionship.io.interfaces.InputReader;
import motocrossWorldChampionship.io.interfaces.OutputWriter;
import motocrossWorldChampionship.repositories.MotorcycleRepository;
import motocrossWorldChampionship.repositories.RaceRepository;
import motocrossWorldChampionship.repositories.RiderRepository;
import motocrossWorldChampionship.repositories.interfaces.Repository;

public class Main {
    public static void main(String[] args) {
        //NE PIPAI SHTOTO NE SLUSHASH!!!!!!!
        Repository<Rider> riderRepository = new RiderRepository();
        Repository<Motorcycle> motorcycleRepository = new MotorcycleRepository();
        Repository<Race> raceRepository = new RaceRepository();

        ChampionshipController controller = new ChampionshipControllerImpl(riderRepository, motorcycleRepository, raceRepository);

        Engine engine = new EngineImpl(controller);
        engine.run();

    }
}
