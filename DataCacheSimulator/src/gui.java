/* GUI display by Jon Sobocinski
 * placeInCache(), ExistsInACache(), checkCPU2(), memoryDecode() by Nick Duncan
 * 
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class gui {

	private JFrame frame;
	int totalRuns = 1;
	int final_latency_total = 0;

	static int hits = 0;
	static int misses = 0;

	public static int L1_SIZE = 8;
	public static int L2_SIZE = 32;
	public static int L3_SIZE = 128;
	public static int LM1_SIZE = (1024 * 1024) * 8;
	public static int LM2_SIZE = (1024 * 1024 * 1024);

	public static int L1_LATENCY = 1;
	public static int L2_LATENCY = 10;
	public static int L3_LATENCY = 20;
	public static int LM1_LATENCY = 100;
	public static int LM2_READ_LATENCY = 250;
	public static int LM2_WRITE_LATENCY = 400;
	public static int final_latency = 0;

	public static int CACHE_LINE_SIZE = 32;
	public static int NUMBER_OF_WAYS = 1;
	public static StringBuilder sb = new StringBuilder();

	public static JTextArea message;

	static File FILE;

	public static void main(String[] args) {

		gui window = new gui();
		window.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public gui() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setTitle("Data Cache Simulator - ''We Sim Your Data So You Don't Have To''");

		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		message = new JTextArea();
		Font f = new Font("TimesRoman", 21, 21);
		message.setFont(f);

		message.setText("Nick Duncan, Matt Moore, Ed Prokhor & Jon Sobocinski's Data Cache Simulator\n372 Final Assignment Fall Quarter 2014");

		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(message);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel("L1 Size");
		panel.add(lblNewLabel);

		final JSpinner spinner = new JSpinner(new SpinnerNumberModel(8, 8, 16,
				8));
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				L1_SIZE = (int) spinner.getValue();
			}
		});

		panel.add(spinner);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);

		JLabel lblNewLabel_1 = new JLabel("L2 Size");
		panel.add(lblNewLabel_1);

		final JSpinner spinner_1 = new JSpinner(new SpinnerNumberModel(32, 32,
				64, 32));
		spinner_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				L2_SIZE = (int) spinner_1.getValue();
			}
		});
		panel.add(spinner_1);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1);

		JLabel lblNewLabel_2 = new JLabel("L3 Size");
		panel.add(lblNewLabel_2);

		final JSpinner spinner_2 = new JSpinner(new SpinnerNumberModel(128,
				128, 256, 128));
		spinner_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				L3_SIZE = (int) spinner_2.getValue();
			}
		});
		panel.add(spinner_2);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_2);

		JLabel lblNewLabel_3 = new JLabel("L1 Latency");
		panel.add(lblNewLabel_3);

		final JSpinner spinner_3 = new JSpinner(new SpinnerNumberModel(1, 1, 2,
				1));
		spinner_3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				L1_LATENCY = (int) spinner_3.getValue();
			}
		});
		panel.add(spinner_3);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_3);

		JLabel lblNewLabel_4 = new JLabel("L2 Latency");
		panel.add(lblNewLabel_4);

		final JSpinner spinner_4 = new JSpinner(new SpinnerNumberModel(10, 10,
				12, 2));
		spinner_4.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				L2_LATENCY = (int) spinner_4.getValue();
			}
		});
		panel.add(spinner_4);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_4);

		JLabel lblNewLabel_5 = new JLabel("L3 Latency");
		panel.add(lblNewLabel_5);

		final JSpinner spinner_5 = new JSpinner(new SpinnerNumberModel(20, 20,
				25, 5));
		spinner_5.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				L3_LATENCY = (int) spinner_5.getValue();
			}
		});
		panel.add(spinner_5);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_5);

		JLabel lblNewLabel_6 = new JLabel("Cache Line Size");
		panel.add(lblNewLabel_6);

		final JSpinner spinner_6 = new JSpinner(new SpinnerNumberModel(16, 16,
				64, 48));
		spinner_6.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				CACHE_LINE_SIZE = (int) spinner_6.getValue();

			}
		});
		panel.add(spinner_6);

		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_6);

		JLabel lblNewLabel_7 = new JLabel("Cache Associativity");
		panel.add(lblNewLabel_7);
		final JSpinner spinner_7 = new JSpinner(new SpinnerNumberModel(1, 1, 8,
				1));
		spinner_7.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {

				NUMBER_OF_WAYS = (int) spinner_7.getValue();
			}
		});
		panel.add(spinner_7);

		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_7);

		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_8);

		JButton load = new JButton("Load Memory Trace");
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser(System
						.getProperty("user.home") + "/Desktop");
				chooser.setDialogTitle("Please Select Memory Trace File");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Memory Trace", "csv");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {

					FILE = chooser.getSelectedFile();

				}
				if (returnVal == JFileChooser.CANCEL_OPTION) {

					chooser.setVisible(false);
				}

			}
		});
		panel.add(load);

		JButton btnRunSimulation = new JButton("Run Simulation");
		btnRunSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (FILE == null) {
					sb.append("PELASE SELECT A VALID FILE\n");
					message.setText(sb.toString());

				} else {
					// //////////////////////////////

					CPU cpu1 = new CPU(new Cache(L1_SIZE, L1_LATENCY),
							new Cache(L1_SIZE, L1_LATENCY), new Cache(L2_SIZE,
									L2_LATENCY));

					CPU cpu2 = new CPU(new Cache(L1_SIZE, L1_LATENCY),
							new Cache(L1_SIZE, L1_LATENCY), new Cache(L2_SIZE,
									L2_LATENCY));

					Cache l3 = new Cache(L3_SIZE, L3_LATENCY);

					Cache test = new Cache(128, 0);

					int counter = 0;

					CPU current_cpu;
					CPU other_cpu;
					ArrayList<String[]> list = new ArrayList<String[]>();

					try {
						Scanner scanner = new Scanner(FILE);
						while (scanner.hasNextLine()) {

							try {
								list.add(scanner.next().split(","));
							} catch (NoSuchElementException er) {

							}

						}

						for (int i = 0; i < list.size() / 100; i++) {
							current_cpu = cpu1;
							other_cpu = cpu2;

							for (int j = 0; j < 100; j++) {

								String iaString = list.get(j)[0];
								int ia = Integer.valueOf(iaString);
								int writevalue;
								int dataaddress;
								EntryNode node;
								if (list.get(j).length == 1) {
									node = new EntryNode(ia);

								} else {

									String writv = list.get(j)[1];
									String dataA = list.get(j)[2];
									writevalue = Integer.valueOf(writv);
									dataaddress = Integer.valueOf(dataA);
									node = new EntryNode(ia, writevalue,
											dataaddress);
								}
								CacheEntry instruction_entry = new CacheEntry(
										current_cpu.l1i.getTag(node.ia),
										node.ia, node.writeValue);
								placeInCache(current_cpu, other_cpu,
										instruction_entry, l3, current_cpu.l1i,
										other_cpu.l1i);
								if (node.dataAddress > -1) {
									CacheEntry data_entry = new CacheEntry(
											current_cpu.l1d
													.getTag(node.dataAddress),
											node.dataAddress, node.writeValue);
									placeInCache(current_cpu, other_cpu,
											data_entry, l3, current_cpu.l1d,
											other_cpu.l1d);

									counter++;
									if (counter == 199) {
										counter = 0;
									}
								}
							}
							// CPU2
							current_cpu = cpu2;
							other_cpu = cpu1;
							for (int k = 0; k < 100; k++) {

								String iaString = list.get(k)[0];
								int ia = Integer.valueOf(iaString);
								int writevalue;
								int dataaddress;
								EntryNode node;
								if (list.get(k).length == 1) {
									node = new EntryNode(ia);

								} else {

									String writv = list.get(k)[1];
									String dataA = list.get(k)[2];
									writevalue = Integer.valueOf(writv);
									dataaddress = Integer.valueOf(dataA);
									node = new EntryNode(ia, writevalue,
											dataaddress);
								}
								CacheEntry instruction_entry = new CacheEntry(
										current_cpu.l1i.getTag(node.ia),
										node.ia, node.writeValue);
								placeInCache(current_cpu, other_cpu,
										instruction_entry, l3, current_cpu.l1i,
										other_cpu.l1i);
								if (node.dataAddress > -1) {
									CacheEntry data_entry = new CacheEntry(
											current_cpu.l1d
													.getTag(node.dataAddress),
											node.dataAddress, node.writeValue);
									placeInCache(current_cpu, other_cpu,
											data_entry, l3, current_cpu.l1d,
											other_cpu.l1d);

									counter++;
									if (counter == 199) {
										counter = 0;
									}
								}

							}

						}
					} catch (FileNotFoundException ex) {
					}
					/*
					 * THIS COMMENTED OUT CODE PRINTS OUT THE CACHES IF YOU
					 * UNCOMMENT IT.
					 * sb.append("-----------L1i cache------------------" +
					 * "\n"); for (int i = 0; i < cpu1.l1i.entries.length; i++)
					 * {
					 * 
					 * sb.append(cpu1.l1i.entries[i] + "\n"); }
					 * sb.append("-----------L2 cache------------------" +
					 * "\n"); for (int i = 0; i < cpu1.l2.entries.length; i++) {
					 * 
					 * sb.append(cpu1.l2.entries[i] + "\n"); }
					 * sb.append("-----------L3 cache------------------" +
					 * "\n"); for (int i = 0; i < l3.entries.length; i++) {
					 * sb.append(l3.entries[i] + "\n"); }
					 */

					System.out.println(sb.toString());

					System.out.println(final_latency);
					final_latency_total += final_latency;
					sb.append("Run number " + totalRuns + ":\n");
					sb.append("Total Run: " + final_latency + "\n");
					final_latency = 0;

					sb.append("Average Run: " + final_latency_total / totalRuns);
					totalRuns++;
					sb.append("\nHits/Misses: " + hits + "/" + misses);
					hits = 0;
					misses = 0;
					// RUN THE PROGRAM
					sb.append("\n\n\n");
					message.setText(sb.toString());
				}

			}
		});
		panel.add(btnRunSimulation);

		JButton btnExitSimulation = new JButton("Exit");
		btnExitSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});
		panel.add(btnExitSimulation);
	}

	public static boolean existsInACache(CPU cpu1, CacheEntry entry, Cache l3,
			Cache cpu1l1, Cache cpu2l1) {
		boolean exists = true;
		for (int i = 0; i < NUMBER_OF_WAYS; i++) {
			if (cpu1l1.entries[(cpu1l1.getIndex(entry.address) * NUMBER_OF_WAYS)
					+ i] != null
					&& cpu1l1.entries[(cpu1l1.getIndex(entry.address) * NUMBER_OF_WAYS)
							+ i].tag == entry.tag) {
				final_latency += cpu1l1.latency;
				// sb.append("hit in l1i" + " " +
				// cpu1l1.getOffset(entry.address)
				// + "\n");
				exists = true;
				hits++;
				break;
			} else if (cpu1.l2.entries[(cpu1.l2.getIndex(entry.address) * NUMBER_OF_WAYS)
					+ i] != null
					&& cpu1.l2.entries[(cpu1.l2.getIndex(entry.address) * NUMBER_OF_WAYS)
							+ i].tag == cpu1.l2.getTag(entry.address)) {
				final_latency += cpu1l1.latency + cpu1.l2.latency;
				// sb.append("hit in l2" + " " +
				// cpu1.l1i.getOffset(entry.address)
				// + "\n");
				exists = true;
				hits++;
				break;
			} else if (l3.entries[(l3.getIndex(entry.address) * NUMBER_OF_WAYS)
					+ i] != null
					&& l3.entries[(l3.getIndex(entry.address) * NUMBER_OF_WAYS)
							+ i].tag == l3.getTag(entry.address)) {
				final_latency += cpu1l1.latency + cpu1.l2.latency + l3.latency;
				// sb.append("hit in l3" + " " + cpu1l1.getOffset(entry.address)
				// + "\n");
				exists = true;
				hits++;
				break;
			}

			else {
				exists = false;
				misses++;
			}
		}

		return exists;

	}

	public static void placeInCache(CPU cpu1, CPU cpu2, CacheEntry entry,
			Cache l3, Cache cpu1l1, Cache cpu2l1) {
		if (existsInACache(cpu1, entry, l3, cpu1l1, cpu2l1)) {

		} else {
			// THIS ELSE MEANS THE ADDRESS IS NOT IN A CACHE VISIBLE BY CPU1 AND
			// WE
			// NEED
			// TO ADD IT.
			// WHILE REQUEST IN MEMORY IS GOING THROUGH CHECK CPU2.
			final_latency += checkCPU2(entry, cpu2, cpu1l1, cpu2l1)
					+ cpu1l1.latency + cpu1.l2.latency + l3.latency;
			entry.tag = cpu1l1.getTag(entry.address);
			int real_index = cpu1l1.getIndex(entry.address) * NUMBER_OF_WAYS;
			int into_set = -1;
			for (int i = 0; i < NUMBER_OF_WAYS; i++) {
				if (cpu1l1.entries[i + real_index] == null) {
					into_set = i;
					break;
				}
			}
			// if into_set is -1 that means the set is full. (No
			// null
			// values).
			if (into_set == -1) {
				Random rand = new Random();
				into_set = rand.nextInt(NUMBER_OF_WAYS);
				CacheEntry l1_evicted = cpu1l1.entries[real_index + into_set];
				cpu1l1.addEntry(entry, real_index + into_set);
				l1_evicted.tag = cpu1.l2.getTag(l1_evicted.address);
				real_index = cpu1.l2.getIndex(l1_evicted.address)
						* NUMBER_OF_WAYS;
				into_set = -1;
				for (int i = 0; i < NUMBER_OF_WAYS; i++) {
					if (cpu1.l2.entries[i + real_index] == null) {
						into_set = i;
						break;
					}
				}
				if (into_set == -1) {
					into_set = rand.nextInt(NUMBER_OF_WAYS);

					CacheEntry l2_evicted = cpu1.l2.entries[real_index
							+ into_set];
					cpu1.l2.addEntry(l1_evicted, real_index + into_set);
					l2_evicted.tag = l3.getTag(l2_evicted.address);
					real_index = l3.getIndex(l2_evicted.address)
							* NUMBER_OF_WAYS;
					into_set = -1;
					for (int i = 0; i < NUMBER_OF_WAYS; i++) {
						if (l3.entries[i + real_index] == null) {
							into_set = i;
							break;
						}
					}
					if (into_set == -1) {
						into_set = rand.nextInt(NUMBER_OF_WAYS);
						l3.addEntry(l2_evicted, real_index + into_set);
					} else {
						l3.addEntry(l2_evicted, real_index + into_set);
					}
				} else {
					cpu1.l2.addEntry(l1_evicted, real_index + into_set);
				}
			} else {
				cpu1l1.addEntry(entry, real_index + into_set);
			}
		}
	}

	public static int checkCPU2(CacheEntry entry, CPU cpu2, Cache cpu1l1,
			Cache cpu2l1) {
		for (int i = 0; i < NUMBER_OF_WAYS; i++) {
			if (cpu2l1.entries[(cpu2l1.getIndex(entry.address) * NUMBER_OF_WAYS)
					+ i] != null
					&& cpu2l1.entries[(cpu2l1.getIndex(entry.address) * NUMBER_OF_WAYS)
							+ i].tag == entry.tag) {
				System.out
						.println(entry.tag
								+ " and "
								+ cpu2l1.entries[(cpu2l1
										.getIndex(entry.address) * NUMBER_OF_WAYS)
										+ i].tag
								+ "  went from "
								+ entry.mesi
								+ " and "
								+ cpu2l1.entries[(cpu2l1
										.getIndex(entry.address) * NUMBER_OF_WAYS)
										+ i].mesi + " to shared");
				entry.mesi = MesiState.SHARED;
				cpu2l1.entries[(cpu2l1.getIndex(entry.address) * NUMBER_OF_WAYS)
						+ i].mesi = MesiState.SHARED;
				return cpu2l1.latency;
			} else if (cpu2.l2.entries[(cpu2.l2.getIndex(entry.address) * NUMBER_OF_WAYS)
					+ i] != null
					&& cpu2.l2.entries[(cpu2.l2.getIndex(entry.address) * NUMBER_OF_WAYS)
							+ i].tag == entry.tag) {
				System.out
						.println(entry.tag
								+ " and "
								+ cpu2l1.entries[(cpu2l1
										.getIndex(entry.address) * NUMBER_OF_WAYS)
										+ i].tag
								+ "  went from "
								+ entry.mesi
								+ " and "
								+ cpu2l1.entries[(cpu2l1
										.getIndex(entry.address) * NUMBER_OF_WAYS)
										+ i].mesi + " to shared");
				entry.mesi = MesiState.SHARED;
				cpu2l1.entries[(cpu2l1.getIndex(entry.address) * NUMBER_OF_WAYS)
						+ i].mesi = MesiState.SHARED;
				return cpu2.l2.latency;
			}
		}
		return memoryDecode(entry);
	}

	public static int memoryDecode(CacheEntry entry) {
		System.out.println("Grabs from memory");
		if (entry.address < LM1_LATENCY) {
			return LM1_LATENCY;
		} else if (entry.wv == 1) {
			return LM2_WRITE_LATENCY;
		} else {
			return LM2_READ_LATENCY;
		}
	}

}
